/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dodrde.coworking.application.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dodrde.coworking.application.TariffService;
import ru.dodrde.coworking.domain.EntityRepository;
import ru.dodrde.coworking.domain.option.Option;
import ru.dodrde.coworking.domain.tariff.Duration;
import ru.dodrde.coworking.domain.tariff.OptionPrice;
import ru.dodrde.coworking.domain.tariff.Tariff;
import ru.dodrde.coworking.domain.tariff.TariffDescription;
import ru.dodrde.coworking.domain.tariff.TariffOptionRelation;
import ru.dodrde.coworking.domain.tariff.TariffRepository;

/**
 *
 * @author Ильдар
 */
@Service
@Transactional
public class DefaultTariffService extends AbstractCRUDService<Tariff> implements TariffService {

    @Inject
    private TariffRepository tariffRepository;

    @Override
    public void createTariff(TariffDescription description, Duration duration, BigDecimal price, List<OptionPrice> optionPrices) {
        Tariff tariff = new Tariff(description, duration, price);
        for (OptionPrice optionPrice : optionPrices) {
            tariff.getOptionRelations().add(createTariffOptionRelation(tariff, optionPrice));
        }
        tariffRepository.store(tariff);
    }

    @Override
    public void updateTariff(Tariff tariff, List<OptionPrice> optionPrices) {
        for(OptionPrice optionPrice : optionPrices) {
            for(TariffOptionRelation optionRelation : tariff.getOptionRelations()) {
                if(optionPrice.getOption().equals(optionRelation.getOptionPrice().getOption())) {
                    optionRelation.getOptionPrice().setPrice(optionPrice.getPrice());
                }
            }
        }
        intersectOptions(tariff, optionPrices);
        update(tariff);
    }
    
    private void intersectOptions(Tariff tariff, List<OptionPrice> optionPrices) {
        List<TariffOptionRelation> removeOptions = new ArrayList<>();
        List<OptionPrice> addOption = new ArrayList<>();

        for (TariffOptionRelation optionRelation : tariff.getOptionRelations()) {
            boolean finded = false;
            for (OptionPrice optionPrice : optionPrices) {
                if (optionRelation.getOptionPrice().getOption().equals(optionPrice.getOption())) {
                    finded = true;
                    break;
                }
            }
            if (!finded) {
                removeOptions.add(optionRelation);
            }
        }
        tariff.getOptionRelations().removeAll(removeOptions);
        for (OptionPrice optionPrice : optionPrices) {
            boolean finded = false;
            for (TariffOptionRelation optionRelation : tariff.getOptionRelations()) {
                if (optionRelation.getOptionPrice().getOption().equals(optionPrice.getOption())) {
                    finded = true;
                    break;
                }
            }
            if (!finded) {
                addOption.add(optionPrice);
            }
        }
        for (OptionPrice optionPrice : addOption) {
            tariff.getOptionRelations().add(createTariffOptionRelation(tariff, optionPrice));
        }
    }

    @Override
    public List<Tariff> getRelevantTariff(Duration duration) {
        return tariffRepository.findByDuration(duration);
    }

    @Override
    public void attachOption(Tariff tariff, OptionPrice optionPrice) {
        tariff.getOptionRelations().add(createTariffOptionRelation(tariff, optionPrice));
        this.update(tariff);
    }

    private TariffOptionRelation createTariffOptionRelation(Tariff tariff, OptionPrice optionPrice) {
        return new TariffOptionRelation(tariff, optionPrice);
    }

    @Override
    public void detachOption(Tariff tariff, Option option) {
        TariffOptionRelation candidate = null;
        for (TariffOptionRelation relation : tariff.getOptionRelations()) {
            if (relation.getOptionPrice().getOption().equals(option)) {
                candidate = relation;
                break;
            }
        }
        if (candidate != null) {
            tariff.getOptionRelations().remove(candidate);
            this.update(tariff);
        }
    }

    @Override
    protected EntityRepository getRepository() {
        return tariffRepository;
    }

}

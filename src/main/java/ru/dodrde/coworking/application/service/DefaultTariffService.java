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
import ru.dodrde.coworking.domain.tariff.DurationPeriod;
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
    public void createTariff(TariffDescription description, Duration duration, BigDecimal price, List<Option> options) {
        Tariff tariff = new Tariff(description, duration, price);
        for (Option option : options) {
            tariff.getOptionRelations().add(createTariffOptionRelation(tariff, option));
        }
        tariffRepository.store(tariff);
    }

    @Override
    public void updateTariff(Tariff tariff, List<Option> options) {
        List<TariffOptionRelation> removeOptions = new ArrayList<>();
        List<Option> addOption = new ArrayList<>();

        for (TariffOptionRelation optionRelation : tariff.getOptionRelations()) {
            boolean finded = false;
            for (Option option : options) {
                if (optionRelation.getOption().equals(option)) {
                    finded = true;
                    break;
                }
            }
            if (!finded) {
                removeOptions.add(optionRelation);
            }
        }
        tariff.getOptionRelations().removeAll(removeOptions);
        for (Option option : options) {
            boolean finded = false;
            for (TariffOptionRelation optionRelation : tariff.getOptionRelations()) {
                if (optionRelation.getOption().equals(option)) {
                    finded = true;
                    break;
                }
            }
            if (!finded) {
                addOption.add(option);
            }
        }
        for (Option option : addOption) {
            tariff.getOptionRelations().add(createTariffOptionRelation(tariff, option));
        }
        update(tariff);
    }

    @Override
    public List<Tariff> getRelevantTariff(Duration duration) {
        return tariffRepository.findByDuration(duration);
    }

    @Override
    public void attachOption(Tariff tariff, Option option) {
        tariff.getOptionRelations().add(createTariffOptionRelation(tariff, option));
        this.update(tariff);
    }

    private TariffOptionRelation createTariffOptionRelation(Tariff tariff, Option option) {
        return new TariffOptionRelation(tariff, option);
    }

    @Override
    public void detachOption(Tariff tariff, Option option) {
        TariffOptionRelation candidate = null;
        for (TariffOptionRelation relation : tariff.getOptionRelations()) {
            if (relation.getOption().equals(option)) {
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

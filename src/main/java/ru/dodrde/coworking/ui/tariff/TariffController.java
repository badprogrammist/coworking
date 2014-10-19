/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dodrde.coworking.ui.tariff;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dodrde.coworking.application.OptionService;
import ru.dodrde.coworking.application.TariffService;
import ru.dodrde.coworking.domain.option.Option;
import ru.dodrde.coworking.domain.tariff.Tariff;
import ru.dodrde.coworking.domain.tariff.TariffOptionRelation;
import ru.dodrde.coworking.ui.option.dto.OptionListData;
import ru.dodrde.coworking.ui.tariff.dto.TariffListData;
import ru.dodrde.coworking.ui.tariff.dto.TariffEditData;

/**
 *
 * @author Ильдар
 */
@Controller
@RequestMapping("/tariff")
public class TariffController {

    @Inject
    private TariffService tariffService;

    @Inject
    private OptionService optionService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TariffListData> getTariffs() {
        List<TariffListData> tariffs = new ArrayList<>();
        for (Tariff tariff : tariffService.getAll()) {
            TariffListData tariffData = new TariffListData(
                    tariff.getId(),
                    tariff.getDescription().getTitle(),
                    tariff.getPrice().toString(),
                    tariff.getDuration().getPeriodQuantity(),
                    tariff.getDuration().getPeriod().getTitle());
            for (TariffOptionRelation optionRelation : tariff.getOptionRelations()) {
                tariffData.getOptions().add(optionRelation.getOption().getDescription().getTitle());
            }
            tariffs.add(tariffData);
        }
        return tariffs;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addTariff(@RequestBody TariffEditData data) {
        tariffService.createTariff(
                data.generateTariffDescription(),
                data.generateDuration(),
                data.generatePrice(),
                data.generatePlaceAttachStatus(),
                getOptionsFromEditData(data));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TariffEditData getViewTariff(@PathVariable("id") Long id) {
        Tariff tariff = tariffService.get(id);
        if (tariff != null) {
            TariffEditData data = new TariffEditData(
                    tariff.getDescription().getTitle(),
                    tariff.getDescription().getDescription(),
                    tariff.getDuration().getPeriod().getTitle(),
                    tariff.getDuration().getPeriodQuantity(),
                    tariff.getPrice().toString(),
                    tariff.getPlaceAttachStatus().getTitle(),
                    tariff.getId()
            );
            for (TariffOptionRelation relation : tariff.getOptionRelations()) {
                data.getOptions().add(new OptionListData(
                        relation.getOption().getDescription().getTitle(),
                        relation.getOption().getId()
                ));
            }
            return data;
        }
        return new TariffEditData();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteTariff(@PathVariable("id") Long id) {
        tariffService.remove(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updateTariff(@RequestBody TariffEditData data) {
        Tariff tariff = tariffService.get(data.getId());
        if (tariff != null) {
            tariff.setDescription(data.generateTariffDescription());
            tariff.setDuration(data.generateDuration());
            tariff.setPrice(data.generatePrice());
            if (!data.getOptions().isEmpty()) {
                tariffService.updateTariff(tariff, getOptionsFromEditData(data));
            } else {
                tariffService.update(tariff);
            }
        }
    }

    private List<Option> getOptionsFromEditData(TariffEditData data) {
        List<Option> options = new ArrayList<>();
        for (OptionListData optionData : data.getOptions()) {
            Option option = optionService.get(optionData.getId());
            if (option != null) {
                options.add(option);
            }
        }
        return options;
    }

}

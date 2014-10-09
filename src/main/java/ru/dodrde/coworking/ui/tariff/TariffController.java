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
import ru.dodrde.coworking.domain.tariff.Duration;
import ru.dodrde.coworking.domain.tariff.DurationPeriod;
import ru.dodrde.coworking.domain.tariff.Tariff;
import ru.dodrde.coworking.domain.tariff.TariffDescription;
import ru.dodrde.coworking.domain.tariff.TariffOptionRelation;
import ru.dodrde.coworking.ui.option.dto.ListOptionData;
import ru.dodrde.coworking.ui.tariff.dto.ListDurationPeriodData;
import ru.dodrde.coworking.ui.tariff.dto.ListTariffData;
import ru.dodrde.coworking.ui.tariff.dto.EditTariffData;

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

    @RequestMapping(value = "/period", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ListDurationPeriodData> getDurationPeriods() {
        List<ListDurationPeriodData> result = new ArrayList<>();
        for (DurationPeriod period : DurationPeriod.values()) {
            result.add(new ListDurationPeriodData(period.getTitle(), period.name()));
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ListTariffData> getTariffs() {
        List<ListTariffData> tariffs = new ArrayList<>();
        for (Tariff tariff : tariffService.getAll()) {
            ListTariffData tariffData = new ListTariffData(
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
    public void addTariff(@RequestBody EditTariffData data) {
        List<Option> options = new ArrayList<>();
        for (ListOptionData optionData : data.getOptions()) {
            options.add(optionService.get(optionData.getId()));
        }
        tariffService.createTariff(
                data.generateTarifDescription(),
                data.generateDuration(),
                data.generatePrice(),
                options);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EditTariffData getViewTariff(@PathVariable("id") Long id) {
        Tariff tariff = tariffService.get(id);
        if (tariff != null) {
            EditTariffData data = new EditTariffData(
                    tariff.getDescription().getTitle(),
                    tariff.getDescription().getDescription(),
                    tariff.getDuration().getPeriod().getTitle(),
                    tariff.getDuration().getPeriodQuantity(),
                    tariff.getPrice().toString(),
                    tariff.getId()
            );
            for(TariffOptionRelation relation : tariff.getOptionRelations()) {
                data.getOptions().add(new ListOptionData(relation.getOption().getId(), relation.getOption().getDescription().getTitle()));
            }
            return data;
        }
        return new EditTariffData();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteTariff(@PathVariable("id") Long id) {
        tariffService.remove(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updateTariff(@RequestBody EditTariffData data) {
        Tariff tariff = tariffService.get(data.getId());
        if (tariff != null) {
            tariff.setDescription(data.generateTarifDescription());
            tariff.setDuration(data.generateDuration());
            tariff.setPrice(data.generatePrice());
            if (!data.getOptions().isEmpty()) {
                List<Option> options = new ArrayList<>();
                for (ListOptionData optionData : data.getOptions()) {
                    options.add(optionService.get(optionData.getId()));
                }
                tariffService.updateTariff(tariff, options);
            } else {
                tariffService.update(tariff);
            }
        }
    }

}

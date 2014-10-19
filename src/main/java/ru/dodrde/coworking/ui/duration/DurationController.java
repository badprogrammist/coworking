/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dodrde.coworking.ui.duration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dodrde.coworking.domain.tariff.condition.DurationPeriod;
import ru.dodrde.coworking.ui.duration.dto.DurationPeriodListData;

/**
 *
 * @author Ильдар
 */
@Controller
@RequestMapping("/duration")
public class DurationController {

    @RequestMapping(value = "/period", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DurationPeriodListData> getDurationPeriods() {
        List<DurationPeriodListData> result = new ArrayList<>();
        for (DurationPeriod period : DurationPeriod.values()) {
            result.add(new DurationPeriodListData(period.getTitle(), period.name()));
        }
        return result;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.duration.dto;

import ru.dodrde.coworking.domain.tariff.Duration;
import ru.dodrde.coworking.domain.tariff.DurationPeriod;
import ru.dodrde.coworking.ui.DTO;

/**
 *
 * @author Ильдар
 */
public class EditDurationData extends DTO {
    private String durationPeriod;
    private Integer periodQuantity;

    public EditDurationData() {
    }

    public EditDurationData(String durationPeriod, Integer periodQuantity, Long id) {
        super(id);
        this.durationPeriod = durationPeriod;
        this.periodQuantity = periodQuantity;
    }

    public Duration generateDuration() {
        return new Duration(generateDurationPeriod(), getPeriodQuantity());
    }
    
    private DurationPeriod generateDurationPeriod() {
        DurationPeriod period = null;
        try {
            period = DurationPeriod.valueOf(getDurationPeriod());
        } catch(Exception ex) {
            for(DurationPeriod dp : DurationPeriod.values()) {
                if(dp.getTitle().equals(getDurationPeriod())) {
                    period = dp;
                    break;
                }
            }
        }
        return period;
    }
    
    
    public String getDurationPeriod() {
        return durationPeriod;
    }

    public void setDurationPeriod(String durationPeriod) {
        this.durationPeriod = durationPeriod;
    }

    public Integer getPeriodQuantity() {
        return periodQuantity;
    }

    public void setPeriodQuantity(Integer periodQuantity) {
        this.periodQuantity = periodQuantity;
    }
    
    
    
}

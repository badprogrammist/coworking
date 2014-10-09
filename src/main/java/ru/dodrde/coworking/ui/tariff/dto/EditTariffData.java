/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.tariff.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import ru.dodrde.coworking.domain.tariff.Duration;
import ru.dodrde.coworking.domain.tariff.DurationPeriod;
import ru.dodrde.coworking.domain.tariff.TariffDescription;
import ru.dodrde.coworking.ui.DTO;
import ru.dodrde.coworking.ui.option.dto.ListOptionData;

/**
 *
 * @author Ильдар
 */
public class EditTariffData extends DTO {
    
    private String title;
    private String description;
    private String durationPeriod;
    private Integer periodQuantity;
    private String price;
    private List<ListOptionData> options = new ArrayList<>();

    public EditTariffData() {
    }

    public EditTariffData(String title, String description, String durationPeriod, Integer periodQuantity, String price) {
        this.title = title;
        this.description = description;
        this.durationPeriod = durationPeriod;
        this.periodQuantity = periodQuantity;
        this.price = price;
    }
    
    public EditTariffData(String title, String description, String durationPeriod, Integer periodQuantity, String price, Long id) {
        super(id);
        this.title = title;
        this.description = description;
        this.durationPeriod = durationPeriod;
        this.periodQuantity = periodQuantity;
        this.price = price;
    }
    
    public TariffDescription generateTarifDescription() {
        return new TariffDescription(getTitle(), getDescription());
    }
    
    public BigDecimal generatePrice() {
        return new BigDecimal(getPrice());
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
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<ListOptionData> getOptions() {
        return options;
    }

    public void setOptions(List<ListOptionData> options) {
        this.options = options;
    }

    
    
    
}

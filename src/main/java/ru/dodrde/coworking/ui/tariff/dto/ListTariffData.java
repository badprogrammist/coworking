/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.tariff.dto;

import java.util.ArrayList;
import java.util.List;
import ru.dodrde.coworking.ui.DTO;

/**
 *
 * @author Ильдар
 */
public class ListTariffData extends DTO {
    
    private String title;
    private String price;
    private Integer periodQuantity;
    private String durationPeriod;
    private List<String> options = new ArrayList<>();

    public ListTariffData(Long id,String title,String price,Integer periodQuantity,String durationPeriod) {
        super(id);
        this.title = title;
        this.price = price;
        this.periodQuantity = periodQuantity;
        this.durationPeriod = durationPeriod;
    }

    public ListTariffData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getPeriodQuantity() {
        return periodQuantity;
    }

    public void setPeriodQuantity(Integer periodQuantity) {
        this.periodQuantity = periodQuantity;
    }

    public String getDurationPeriod() {
        return durationPeriod;
    }

    public void setDurationPeriod(String durationPeriod) {
        this.durationPeriod = durationPeriod;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
    
    
    
}

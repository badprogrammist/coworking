/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.tariff.dto;

import ru.dodrde.coworking.ui.duration.dto.EditDurationData;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import ru.dodrde.coworking.domain.tariff.TariffDescription;
import ru.dodrde.coworking.ui.option.dto.ListOptionData;

/**
 *
 * @author Ильдар
 */
public class EditTariffData extends EditDurationData {
    
    private String title;
    private String description;
    private String price;
    private List<ListOptionData> options = new ArrayList<>();

    public EditTariffData() {
    }

    public EditTariffData(String title, String description, String durationPeriod, Integer periodQuantity, String price, Long id) {
        super(durationPeriod,periodQuantity,id);
        this.title = title;
        this.description = description;
        this.price = price;
    }
    
    public TariffDescription generateTarifDescription() {
        return new TariffDescription(getTitle(), getDescription());
    }
    
    public BigDecimal generatePrice() {
        return new BigDecimal(getPrice());
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

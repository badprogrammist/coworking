/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.tariff.dto;

import ru.dodrde.coworking.ui.duration.dto.DurationEditData;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import ru.dodrde.coworking.domain.tariff.TariffDescription;
import ru.dodrde.coworking.domain.tariff.condition.PlaceAttachStatus;
import ru.dodrde.coworking.ui.option.dto.OptionListData;

/**
 *
 * @author Ильдар
 */
public class TariffEditData extends DurationEditData {
    
    private String title;
    private String description;
    private String price;
    private String placeAttachStatus;
    private List<OptionListData> options = new ArrayList<>();

    public TariffEditData() {
    }

    public TariffEditData(String title, String description, String durationPeriod, Integer periodQuantity, String price,String placeAttachStatus, Long id) {
        super(durationPeriod,periodQuantity,id);
        this.title = title;
        this.description = description;
        this.price = price;
        this.placeAttachStatus = placeAttachStatus;
    }
    
    public PlaceAttachStatus generatePlaceAttachStatus() {
        PlaceAttachStatus status = null;
        try {
            status = PlaceAttachStatus.valueOf(placeAttachStatus);
        } catch(Exception ex) {
            for(PlaceAttachStatus pas : PlaceAttachStatus.values()) {
                if(pas.getTitle().equals(placeAttachStatus)) {
                    status = pas;
                    break;
                }
            }
        }
        return status;
    }
    
    public TariffDescription generateTariffDescription() {
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

    public List<OptionListData> getOptions() {
        return options;
    }

    public void setOptions(List<OptionListData> options) {
        this.options = options;
    }

    public String getPlaceAttachStatus() {
        return placeAttachStatus;
    }

    public void setPlaceAttachStatus(String placeAttachStatus) {
        this.placeAttachStatus = placeAttachStatus;
    }

    
    
    
    
}

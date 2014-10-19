/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.booking.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.dodrde.coworking.ui.option.dto.OptionListData;

/**
 *
 * @author Ильдар
 */
public class UserReservationData {
    private Date fromTime;
    private Date toTime;
    private String price;
    private List<OptionListData> options = new ArrayList<>();

    public UserReservationData() {
    }

    public UserReservationData(Date fromTime, Date toDate,String price) {
        this.fromTime = fromTime;
        this.toTime = toDate;
        this.price = price;
    }


    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
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
    
    
    
}

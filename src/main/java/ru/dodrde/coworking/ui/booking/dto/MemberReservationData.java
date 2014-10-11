/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.booking.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.dodrde.coworking.ui.option.dto.ListOptionData;

/**
 *
 * @author Ильдар
 */
public class MemberReservationData {
    private Long placeId;
    private String placeTitle;
    private Date fromTime;
    private Date toTime;
    private String price;
    private List<ListOptionData> options = new ArrayList<>();

    public MemberReservationData() {
    }

    public MemberReservationData(Long placeId, String placeTitle, Date fromTime, Date toDate,String price) {
        this.placeId = placeId;
        this.placeTitle = placeTitle;
        this.fromTime = fromTime;
        this.toTime = toDate;
        this.price = price;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
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

    public List<ListOptionData> getOptions() {
        return options;
    }

    public void setOptions(List<ListOptionData> options) {
        this.options = options;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.booking.dto;

import java.util.Date;

/**
 *
 * @author Ильдар
 */
public class MemberReservationData {
    private Long placeId;
    private String placeTitle;
    private Date fromTime;
    private Date toTime;

    public MemberReservationData() {
    }

    public MemberReservationData(Long placeId, String placeTitle, Date fromTime, Date toDate) {
        this.placeId = placeId;
        this.placeTitle = placeTitle;
        this.fromTime = fromTime;
        this.toTime = toDate;
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
    
    
    
}

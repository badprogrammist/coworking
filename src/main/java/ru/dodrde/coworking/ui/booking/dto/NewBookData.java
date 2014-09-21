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
public class NewBookData {
    private Long memberId;
    private Long placeId;
    private Date fromTime;
    private Date toTime;

    public NewBookData() {
    }

    public NewBookData(Long member, Long place, Date fromTime) {
        this.memberId = member;
        this.placeId = place;
        this.fromTime = fromTime;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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

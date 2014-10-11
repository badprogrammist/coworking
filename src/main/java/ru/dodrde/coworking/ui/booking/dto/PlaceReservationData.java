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
public class PlaceReservationData {
    private Long memberId;
    private String memberFullname;
    private Date fromTime;
    private Date toTime;

    public PlaceReservationData() {
    }

    public PlaceReservationData(Long placeId, String placeTitle, Date fromTime, Date toDate) {
        this.memberId = placeId;
        this.memberFullname = placeTitle;
        this.fromTime = fromTime;
        this.toTime = toDate;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberFullname() {
        return memberFullname;
    }

    public void setMemberFullname(String memberFullname) {
        this.memberFullname = memberFullname;
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

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
public class BookEditData {
    private Long memberId;
    private Long tariffId;
    private Date fromTime;

    public BookEditData() {
    }

    public BookEditData(Long member, Date fromTime,Long tariffId) {
        this.memberId = member;
        this.fromTime = fromTime;
        this.tariffId = tariffId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    
}

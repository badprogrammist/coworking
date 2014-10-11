/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.booking.dto;

import java.util.Date;
import static ru.dodrde.coworking.domain.tariff.Duration_.periodQuantity;

/**
 *
 * @author Ильдар
 */
public class EditBookData {
    private Long memberId;
    private Long placeId;
    private Long tariffId;
    private Date fromTime;

    public EditBookData() {
    }

    public EditBookData(Long member, Long place, Date fromTime,Long tariffId) {
        this.memberId = member;
        this.placeId = place;
        this.fromTime = fromTime;
        this.tariffId = tariffId;
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

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    
}

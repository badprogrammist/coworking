/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.booking;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ильдар
 */
@Embeddable
public class ReservationPeriod implements Serializable {
    
    @Column(name="from_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromTime;
    
    @Column(name="to_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toTime;

    public ReservationPeriod() {
    }

    public ReservationPeriod(Date fromTime, Date toTime) {
        this.fromTime = fromTime;
        this.toTime = toTime;
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

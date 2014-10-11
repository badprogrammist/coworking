/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.booking;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import ru.dodrde.coworking.domain.tariff.Duration;

/**
 *
 * @author Ильдар
 */
@Embeddable
public class ReservationPeriod implements Serializable {
    
    @Column(name="from_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromTime;
    
    @Embedded
    private Duration duration = new Duration();

    public ReservationPeriod() {
    }

    public ReservationPeriod(Date fromTime, Duration duration) {
        this.fromTime = fromTime;
        this.duration = duration;
    }
    
    public Date getToTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromTime);
        calendar.add(duration.getPeriod().getCalendarConstant(),duration.getPeriodQuantity());
        return calendar.getTime();
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
    
    
}

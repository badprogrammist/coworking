/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.booking;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import ru.dodrde.coworking.domain.user.User;
import ru.dodrde.coworking.domain.tariff.condition.Condition;
import ru.dodrde.coworking.domain.tariff.condition.Duration;
import ru.dodrde.coworking.domain.tariff.condition.PlaceAttachStatus;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "reservations")
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll",query = "Select c from Reservation  c"),
    @NamedQuery(name = "Reservation.findByUser",query = "Select c from Reservation c WHERE c.user = :user")
})
public class Reservation extends Condition<ReservationOptionRelation> {
    
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
    @Column(name="from_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromTime;
    
    public Reservation() {
    }

    public Reservation(User user, Date fromTime, Duration duration, BigDecimal price, PlaceAttachStatus placeAttachStatus) {
        super(duration, price, placeAttachStatus);
        this.user = user;
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromTime);
        calendar.add(getDuration().getPeriod().getCalendarConstant(),getDuration().getPeriodQuantity());
        return calendar.getTime();
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }
    
}

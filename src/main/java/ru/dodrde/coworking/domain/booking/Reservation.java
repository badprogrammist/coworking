/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.booking;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.dodrde.coworking.domain.AbstractEntity;
import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.place.Place;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "reservations")
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll",query = "Select c from Reservation  c"),
    @NamedQuery(name = "Reservation.findByMember",query = "Select c from Reservation c WHERE c.member = :member"),
    @NamedQuery(name = "Reservation.findByPlace",query = "Select c from Reservation c WHERE c.place = :place")
})
public class Reservation extends AbstractEntity {
    
    @JoinColumn(name = "place_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;
    
    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CoworkingMember member;
    
    @Embedded
    private ReservationPeriod reservationPeriod;

    public Reservation() {
    }
    
    public Reservation(Place place, CoworkingMember member, ReservationPeriod reservationPeriod) {
        this.place = place;
        this.member = member;
        this.reservationPeriod = reservationPeriod;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public CoworkingMember getMember() {
        return member;
    }

    public void setMember(CoworkingMember member) {
        this.member = member;
    }

    public ReservationPeriod getReservationPeriod() {
        return reservationPeriod;
    }

    public void setReservationPeriod(ReservationPeriod reservationPeriod) {
        this.reservationPeriod = reservationPeriod;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.booking;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.dodrde.coworking.domain.AbstractEntity;
import ru.dodrde.coworking.domain.option.Option;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "reservation_option_relations")
@NamedQueries({
    @NamedQuery(name = "ReservationOptionRelation.findAll",
            query = "Select c from ReservationOptionRelation c"),
    @NamedQuery(name = "ReservationOptionRelation.findByOption",
            query = "Select c from ReservationOptionRelation c WHERE c.option = :option"),
    @NamedQuery(name = "ReservationOptionRelation.findByTariff",
            query = "Select c from ReservationOptionRelation c WHERE c.reservation = :reservation")
})
public class ReservationOptionRelation extends AbstractEntity {
    
    @JoinColumn(name = "reservation_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Reservation reservation;
    
    @JoinColumn(name = "option_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;

    public ReservationOptionRelation() {
    }

    public ReservationOptionRelation(Reservation reservation, Option option) {
        this.reservation = reservation;
        this.option = option;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
    
    
    
}

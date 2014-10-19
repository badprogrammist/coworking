/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.booking;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.dodrde.coworking.domain.option.Option;
import ru.dodrde.coworking.domain.tariff.condition.ConditionOptionRelation;

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
    @NamedQuery(name = "ReservationOptionRelation.findByReservation",
            query = "Select c from ReservationOptionRelation c WHERE c.condition = :condition")
})
public class ReservationOptionRelation extends ConditionOptionRelation<Reservation> {

    public ReservationOptionRelation() {
    }

    public ReservationOptionRelation(Reservation condition, Option option) {
        super(condition, option);
    }
    
    
    
}

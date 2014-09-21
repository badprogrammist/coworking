/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application;

import java.util.List;
import ru.dodrde.coworking.domain.booking.Reservation;
import ru.dodrde.coworking.domain.booking.ReservationPeriod;
import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.place.Place;

/**
 *
 * @author Ильдар
 */
public interface BookingService {
    public void book(CoworkingMember coworkingMember, Place place, ReservationPeriod reservationPeriod);
    public List<Reservation> getMemberReservations(CoworkingMember member);
    public List<Reservation> getPlaceReservations(Place place);
}

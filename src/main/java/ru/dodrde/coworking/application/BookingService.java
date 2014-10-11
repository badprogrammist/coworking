/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application;

import java.util.Date;
import java.util.List;
import ru.dodrde.coworking.domain.booking.Reservation;
import ru.dodrde.coworking.domain.booking.ReservationPeriod;
import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.place.Place;
import ru.dodrde.coworking.domain.tariff.Tariff;

/**
 *
 * @author Ильдар
 */
public interface BookingService {
    public void book(CoworkingMember coworkingMember, Place place,Tariff tariff, Date fromTime);
    public boolean isPlaceVacantAtGivenPeriod(Place place, ReservationPeriod rP);
    public List<Reservation> getMemberReservations(CoworkingMember member);
    public List<Reservation> getPlaceReservations(Place place);
}

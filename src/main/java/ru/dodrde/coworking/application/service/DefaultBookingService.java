/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dodrde.coworking.application.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dodrde.coworking.application.BookingService;
import ru.dodrde.coworking.domain.booking.Reservation;
import ru.dodrde.coworking.domain.booking.ReservationPeriod;
import ru.dodrde.coworking.domain.booking.ReservationRepository;
import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.place.Place;

/**
 *
 * @author Ильдар
 */
@Service
@Transactional
public class DefaultBookingService implements BookingService {

    @Inject
    private ReservationRepository reservationRepository;

    // TODO добавить проверку
    @Override
    public void book(CoworkingMember coworkingMember, Place place, ReservationPeriod reservationPeriod) {
        if (checkReservationPeriod(place, reservationPeriod)) {
            Reservation reservation = new Reservation(place, coworkingMember, reservationPeriod);
            reservationRepository.store(reservation);
        }
    }

    private boolean checkReservationPeriod(Place place, ReservationPeriod rP) {
        if (rP.getFromTime() != null
                && rP.getToTime() != null
                && rP.getFromTime().before(rP.getToTime())) {
            List<Reservation> placeReservations = getPlaceReservations(place);
            for (Reservation placeReservation : placeReservations) {
                ReservationPeriod placeRP = placeReservation.getReservationPeriod();
                if ((placeRP.getFromTime().before(rP.getToTime())) && (placeRP.getToTime().before(rP.getFromTime()))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public List<Reservation> getMemberReservations(CoworkingMember member) {
        return reservationRepository.getMemberReservations(member);
    }

    @Override
    public List<Reservation> getPlaceReservations(Place place) {
        return reservationRepository.getPlaceReservations(place);
    }

}

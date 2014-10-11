/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dodrde.coworking.application.service;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dodrde.coworking.application.BookingService;
import ru.dodrde.coworking.domain.booking.Reservation;
import ru.dodrde.coworking.domain.booking.ReservationOptionRelation;
import ru.dodrde.coworking.domain.booking.ReservationPeriod;
import ru.dodrde.coworking.domain.booking.ReservationRepository;
import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.option.Option;
import ru.dodrde.coworking.domain.place.Place;
import ru.dodrde.coworking.domain.tariff.Tariff;
import ru.dodrde.coworking.domain.tariff.TariffOptionRelation;

/**
 *
 * @author Ильдар
 */
@Service
@Transactional
public class DefaultBookingService implements BookingService {

    @Inject
    private ReservationRepository reservationRepository;

    @Override
    public void book(CoworkingMember coworkingMember, Place place,Tariff tariff, Date fromTime) {
        ReservationPeriod reservationPeriod = new ReservationPeriod(fromTime, tariff.getDuration());
        if (isPlaceVacantAtGivenPeriod(place, reservationPeriod)) {
            Reservation reservation = new Reservation(place, coworkingMember, reservationPeriod, tariff.getTotalPrice());
            for(TariffOptionRelation tariffOptionRelation : tariff.getOptionRelations()) {
                reservation.getOptionRelations().add(createOptionRelation(reservation, tariffOptionRelation.getOptionPrice().getOption()));
            }
            reservationRepository.store(reservation);
        }
    }
    
    private ReservationOptionRelation createOptionRelation(Reservation reservation, Option option) {
        return new ReservationOptionRelation(reservation, option);
    }

    @Override
    public boolean isPlaceVacantAtGivenPeriod(Place place, ReservationPeriod rP) {
        if (rP.getFromTime() != null && rP.getToTime() != null && rP.getFromTime().before(rP.getToTime())) {
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

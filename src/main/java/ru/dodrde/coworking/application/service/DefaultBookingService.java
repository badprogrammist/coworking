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
import ru.dodrde.coworking.domain.booking.ReservationRepository;
import ru.dodrde.coworking.domain.user.User;
import ru.dodrde.coworking.domain.option.Option;
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
    public void book(User user, Tariff tariff, Date fromTime) {
        Reservation reservation = new Reservation(user, fromTime, tariff.getDuration(),tariff.getPrice(),tariff.getPlaceAttachStatus());
        for (TariffOptionRelation tariffOptionRelation : tariff.getOptionRelations()) {
            reservation.getOptionRelations().add(createOptionRelation(reservation, tariffOptionRelation.getOption()));
        }
        reservationRepository.store(reservation);
    }
    
    private ReservationOptionRelation createOptionRelation(Reservation reservation, Option option) {
        return new ReservationOptionRelation(reservation, option);
    }

    @Override
    public List<Reservation> getMemberReservations(User member) {
        return reservationRepository.getUserReservations(member);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application;

import java.util.Date;
import java.util.List;
import ru.dodrde.coworking.domain.booking.Reservation;
import ru.dodrde.coworking.domain.user.User;
import ru.dodrde.coworking.domain.tariff.Tariff;

/**
 *
 * @author Ильдар
 */
public interface BookingService {
    public void book(User user, Tariff tariff, Date fromTime);
    public List<Reservation> getMemberReservations(User member);
}

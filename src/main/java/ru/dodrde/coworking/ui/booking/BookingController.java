/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dodrde.coworking.ui.booking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dodrde.coworking.application.BookingService;
import ru.dodrde.coworking.application.UserRegistrationService;
import ru.dodrde.coworking.application.TariffService;
import ru.dodrde.coworking.domain.booking.Reservation;
import ru.dodrde.coworking.domain.booking.ReservationOptionRelation;
import ru.dodrde.coworking.domain.user.User;
import ru.dodrde.coworking.domain.tariff.Tariff;
import ru.dodrde.coworking.ui.booking.dto.BookEditData;
import ru.dodrde.coworking.ui.booking.dto.UserReservationData;
import ru.dodrde.coworking.ui.option.dto.OptionListData;

/**
 *
 * @author Ильдар
 */
@Controller
@RequestMapping("/booking")
public class BookingController {

    @Inject
    private UserRegistrationService userRegistrationService;

    @Inject
    private BookingService bookingService;

    @Inject
    private TariffService tariffService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void book(@RequestBody BookEditData data) {
        User member = userRegistrationService.get(data.getMemberId());
        Tariff tariff = tariffService.get(data.getTariffId());
        if (member != null && tariff != null) {
            bookingService.book(member, tariff, data.getFromTime());
        }
    }

    @RequestMapping(method = RequestMethod.GET, params = "userId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserReservationData> getUserReservations(@RequestParam("userId") Long memberId) {
        User member = userRegistrationService.get(memberId);
        if (member != null) {
            List<UserReservationData> reservations = new ArrayList<>();
            for (Reservation reservation : bookingService.getMemberReservations(member)) {
                Date fromTime = reservation.getFromTime();
                Date toTime = reservation.getToTime();
                UserReservationData data = new UserReservationData(fromTime, toTime, reservation.getPrice().toString());
                for (ReservationOptionRelation optionRelation : reservation.getOptionRelations()) {
                    data.getOptions().add(new OptionListData(optionRelation.getOption().getDescription().getTitle(), optionRelation.getOption().getId()));
                }
                reservations.add(data);
            }
            return reservations;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

}

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
import ru.dodrde.coworking.application.MemberRegistrationService;
import ru.dodrde.coworking.application.PlaceService;
import ru.dodrde.coworking.domain.booking.Reservation;
import ru.dodrde.coworking.domain.booking.ReservationPeriod;
import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.place.Place;
import ru.dodrde.coworking.ui.booking.dto.NewBookData;
import ru.dodrde.coworking.ui.booking.dto.MemberReservationData;

/**
 *
 * @author Ильдар
 */
@Controller
@RequestMapping("/booking")
public class BookingController {

    @Inject
    private MemberRegistrationService memberRegistrationService;

    @Inject
    private PlaceService placeService;

    @Inject
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void book(@RequestBody NewBookData data) {
        Place place = placeService.get(data.getPlaceId());
        CoworkingMember member = memberRegistrationService.get(data.getMemberId());
        ReservationPeriod period = new ReservationPeriod(data.getFromTime(), data.getToTime());
        if (place != null && member != null) {
            bookingService.book(member, place, period);
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MemberReservationData> getMemberReservations(@RequestParam("memberId") Long memberId) {
        CoworkingMember member = memberRegistrationService.get(memberId);
        if (member != null) {
            List<MemberReservationData> reservations = new ArrayList<>();
            for (Reservation reservation : bookingService.getMemberReservations(member)) {
                Long placeId = reservation.getPlace().getId();
                String placeTitle = reservation.getPlace().getTitle();
                Date fromTime = reservation.getReservationPeriod().getFromTime();
                Date toTime = reservation.getReservationPeriod().getToTime();
                reservations.add(new MemberReservationData(placeId, placeTitle, fromTime, toTime));
            }
            return reservations;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

}

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
import ru.dodrde.coworking.application.TariffService;
import ru.dodrde.coworking.domain.booking.Reservation;
import ru.dodrde.coworking.domain.booking.ReservationOptionRelation;
import ru.dodrde.coworking.domain.booking.ReservationPeriod;
import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.place.Place;
import ru.dodrde.coworking.domain.tariff.Tariff;
import ru.dodrde.coworking.ui.booking.dto.EditBookData;
import ru.dodrde.coworking.ui.booking.dto.MemberReservationData;
import ru.dodrde.coworking.ui.booking.dto.PlaceReservationData;
import ru.dodrde.coworking.ui.option.dto.ListOptionData;

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
    
    @Inject
    private TariffService tariffService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void book(@RequestBody EditBookData data) {
        Place place = placeService.get(data.getPlaceId());
        CoworkingMember member = memberRegistrationService.get(data.getMemberId());
        Tariff tariff = tariffService.get(data.getTariffId());
        if (place != null && member != null && tariff != null) {
            bookingService.book(member, place, tariff, data.getFromTime());
        }
    }

    @RequestMapping(method = RequestMethod.GET,params = "memberId", produces = MediaType.APPLICATION_JSON_VALUE)
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
                MemberReservationData data = new MemberReservationData(placeId, placeTitle, fromTime, toTime, reservation.getTotalCost().toString());
                for(ReservationOptionRelation r : reservation.getOptionRelations()) {
                    data.getOptions().add(new ListOptionData(r.getOption().getDescription().getTitle(), "0", r.getOption().getId()));
                }
                
                reservations.add(data);
            }
            return reservations;
        } else {
            return Collections.EMPTY_LIST;
        }
    }
    
    @RequestMapping(method = RequestMethod.GET,params = "placeId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PlaceReservationData> getPlaceReservations(@RequestParam("placeId") Long placeId) {
        Place place = placeService.get(placeId);
        if (place != null) {
            List<PlaceReservationData> reservations = new ArrayList<>();
            for (Reservation reservation : bookingService.getPlaceReservations(place)) {
                Long memberId = reservation.getMember().getId();
                String memberFullname = reservation.getMember().getMemberData().getFullname();
                Date fromTime = reservation.getReservationPeriod().getFromTime();
                Date toTime = reservation.getReservationPeriod().getToTime();
                reservations.add(new PlaceReservationData(memberId, memberFullname, fromTime, toTime));
            }
            return reservations;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

}

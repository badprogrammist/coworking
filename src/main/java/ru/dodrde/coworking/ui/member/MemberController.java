/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dodrde.coworking.ui.member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dodrde.coworking.application.BookingService;
import ru.dodrde.coworking.application.MemberRegistrationService;
import ru.dodrde.coworking.domain.booking.Reservation;
import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.member.CoworkingMemberData;
import ru.dodrde.coworking.ui.booking.dto.MemberReservationData;
import ru.dodrde.coworking.ui.member.dto.ListMemberData;
import ru.dodrde.coworking.ui.member.dto.NewMemberData;
import ru.dodrde.coworking.ui.member.dto.ViewMemberData;

/**
 *
 * @author Ильдар
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @Inject
    private MemberRegistrationService registrationService;

    @Inject
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ListMemberData> getMembers() {
        List<ListMemberData> members = new ArrayList<>();
        for (CoworkingMember member : registrationService.getCoworkingMembers()) {
            members.add(new ListMemberData(member.getId(), member.getMemberData().getName(), member.getMemberData().getLastname(), member.getMemberData().getPatronymic()));
        }
        return members;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addMember(@RequestBody NewMemberData data) {
        CoworkingMemberData memberData = new CoworkingMemberData(data.getName(), data.getLastname(), data.getPatronymic());
        registrationService.registerMember(memberData);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ViewMemberData getMember(@PathVariable("id") Long id) {
        CoworkingMember member = registrationService.getCoworkingMember(id);
        if (member != null) {
            ViewMemberData data = new ViewMemberData(member.getId(), member.getMemberData().getName(), member.getMemberData().getLastname(), member.getMemberData().getPatronymic());
            return data;
        }
        return new ViewMemberData();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteMember(@PathVariable("id") Long id) {
        registrationService.removeCoworkingMember(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updateMember(@RequestBody ViewMemberData data) {
        CoworkingMember member = registrationService.getCoworkingMember(data.getId());
        if (member != null) {
            member.getMemberData().setName(data.getName());
            member.getMemberData().setLastname(data.getLastname());
            member.getMemberData().setPatronymic(data.getPatronymic());
            registrationService.updateCoworkingMember(member);
        }
    }

    
}

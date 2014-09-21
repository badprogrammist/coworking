/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.booking;

import java.util.List;
import ru.dodrde.coworking.domain.EntityRepository;
import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.place.Place;

/**
 *
 * @author Ильдар
 */
public interface ReservationRepository extends EntityRepository<Reservation> {
    public List<Reservation> getMemberReservations(CoworkingMember member);
    public List<Reservation> getPlaceReservations(Place place);
}

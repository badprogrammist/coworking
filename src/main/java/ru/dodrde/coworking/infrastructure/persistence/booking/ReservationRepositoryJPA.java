/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.infrastructure.persistence.booking;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.dodrde.coworking.domain.booking.Reservation;
import ru.dodrde.coworking.domain.booking.ReservationRepository;
import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.place.Place;
import ru.dodrde.coworking.infrastructure.persistence.AbstractRepositoryJPA;

/**
 *
 * @author Ильдар
 */
@Repository
public class ReservationRepositoryJPA extends AbstractRepositoryJPA<Reservation> implements ReservationRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public ReservationRepositoryJPA() {
        super(Reservation.class);
    }

    @Override
    public List<Reservation> getMemberReservations(CoworkingMember member) {
        return getEntityManager().createNamedQuery("Reservation.findByMember", Reservation.class)
                .setParameter("member", member)
                .getResultList();
    }

    @Override
    public List<Reservation> getPlaceReservations(Place place) {
        return getEntityManager().createNamedQuery("Reservation.findByPlace", Reservation.class)
                .setParameter("place", place)
                .getResultList();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}

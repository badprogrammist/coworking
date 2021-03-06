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
import ru.dodrde.coworking.domain.user.User;
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
    public List<Reservation> getUserReservations(User user) {
        return getEntityManager().createNamedQuery("Reservation.findByUser", Reservation.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}

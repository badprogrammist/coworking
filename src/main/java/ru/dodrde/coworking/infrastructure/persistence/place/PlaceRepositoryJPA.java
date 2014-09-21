/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.infrastructure.persistence.place;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.dodrde.coworking.domain.place.Place;
import ru.dodrde.coworking.domain.place.PlaceRepository;
import ru.dodrde.coworking.infrastructure.persistence.AbstractRepositoryJPA;

/**
 *
 * @author Ильдар
 */
@Repository
public class PlaceRepositoryJPA extends AbstractRepositoryJPA<Place> implements PlaceRepository {

    @PersistenceContext
    private EntityManager entityManager;
    
    public PlaceRepositoryJPA() {
        super(Place.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
}

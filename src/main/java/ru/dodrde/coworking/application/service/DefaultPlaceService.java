/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dodrde.coworking.application.PlaceService;
import ru.dodrde.coworking.domain.EntityRepository;
import ru.dodrde.coworking.domain.place.Place;
import ru.dodrde.coworking.domain.place.PlaceRepository;

/**
 *
 * @author Ильдар
 */
@Service
@Transactional
public class DefaultPlaceService extends AbstractCRUDService<Place> implements PlaceService {

    @Inject
    private PlaceRepository placeRepository;
    
    @Override
    public void createPlace(String title, Integer capacity) {
        Place place = new Place(title,capacity);
        placeRepository.store(place);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    protected EntityRepository getRepository() {
        return placeRepository;
    }
    
}

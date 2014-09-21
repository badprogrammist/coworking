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
import ru.dodrde.coworking.domain.place.Place;
import ru.dodrde.coworking.domain.place.PlaceRepository;

/**
 *
 * @author Ильдар
 */
@Service
@Transactional
public class DefaultPlaceService implements PlaceService {

    @Inject
    private PlaceRepository placeRepository;
    
    @Override
    public void createPlace(String title) {
        Place place = new Place(title);
        placeRepository.store(place);
    }

    @Override
    public List<Place> getPlaces() {
        return placeRepository.getAll();
    }

    @Override
    public Place getPlace(Long id) {
        return placeRepository.get(id);
    }

    @Override
    public void updatePlace(Place place) {
        placeRepository.update(place);
    }

    @Override
    public void removePlace(Long id) {
    }
    
}

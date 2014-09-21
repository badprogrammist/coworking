/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application;

import java.util.List;
import ru.dodrde.coworking.domain.place.Place;

/**
 *
 * @author Ильдар
 */
public interface PlaceService {
    public void createPlace(String title);
    public List<Place> getPlaces();
    public Place getPlace(Long id);
    public void updatePlace(Place place);
    public void removePlace(Long id);
}

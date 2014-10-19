/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application;

import ru.dodrde.coworking.domain.place.Place;

/**
 *
 * @author Ильдар
 */
public interface PlaceService extends CRUDService<Place> {
    public void createPlace(String title, Integer capacity);
}

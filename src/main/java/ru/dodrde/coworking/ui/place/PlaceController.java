/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.place;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dodrde.coworking.application.PlaceService;
import ru.dodrde.coworking.domain.place.Place;
import ru.dodrde.coworking.ui.place.dto.ListPlaceData;
import ru.dodrde.coworking.ui.place.dto.NewPlaceData;
import ru.dodrde.coworking.ui.place.dto.ViewPlaceData;

/**
 *
 * @author Ильдар
 */
@Controller
@RequestMapping("/place")
public class PlaceController {
    
    @Inject
    private PlaceService placeService;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ListPlaceData> getPlaces() {
        List<ListPlaceData> places = new ArrayList<>();
        for(Place place : placeService.getPlaces()) {
            places.add(new ListPlaceData(place.getId(), place.getTitle()));
        }
        return places;
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addPlace(@RequestBody NewPlaceData data) {
        placeService.createPlace(data.getTitle());
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ViewPlaceData getPlace(@PathVariable("id") Long id) {
        Place place = placeService.getPlace(id);
        if(place != null) {
            ViewPlaceData data = new ViewPlaceData(place.getId(), place.getTitle());
            return data;
        }
        return new ViewPlaceData();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deletePlace(@PathVariable("id") Long id) {
        placeService.removePlace(id);
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updatePlace(@RequestBody ViewPlaceData data) {
        Place place = placeService.getPlace(data.getId());
        if(place != null) {
            place.setTitle(data.getTitle());
            placeService.updatePlace(place);
        }
    }
}

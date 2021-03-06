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
import ru.dodrde.coworking.ui.place.dto.PlaceListData;
import ru.dodrde.coworking.ui.place.dto.PlaceEditData;

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
    public List<PlaceListData> getPlaces() {
        List<PlaceListData> places = new ArrayList<>();
        for(Place place : placeService.getAll()) {
            places.add(new PlaceListData(place.getId(), place.getTitle()));
        }
        return places;
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addPlace(@RequestBody PlaceEditData data) {
        placeService.createPlace(data.getTitle(),data.getCapacity());
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PlaceEditData getPlace(@PathVariable("id") Long id) {
        Place place = placeService.get(id);
        if(place != null) {
            PlaceEditData data = new PlaceEditData( place.getTitle(), place.getCapacity(), place.getId());
            return data;
        }
        return new PlaceEditData();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deletePlace(@PathVariable("id") Long id) {
        placeService.remove(id);
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updatePlace(@RequestBody PlaceEditData data) {
        Place place = placeService.get(data.getId());
        if(place != null) {
            place.setTitle(data.getTitle());
            place.setCapacity(data.getCapacity());
            placeService.update(place);
        }
    }
}

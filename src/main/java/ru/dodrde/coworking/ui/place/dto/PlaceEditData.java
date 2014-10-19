/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.place.dto;

import ru.dodrde.coworking.ui.DTO;

/**
 *
 * @author Ильдар
 */
public class PlaceEditData extends DTO {
    private String title;
    private Integer capacity = 1;

    public PlaceEditData() {
    }

    public PlaceEditData(String title, Integer capacity) {
        this.title = title;
        this.capacity = capacity;
    }

    public PlaceEditData(String title, Integer capacity, Long id) {
        super(id);
        this.title = title;
        this.capacity = capacity;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    
    
}

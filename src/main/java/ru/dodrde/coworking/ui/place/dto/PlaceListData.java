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
public class PlaceListData extends DTO {
    private String title;

    public PlaceListData(Long id,String title) {
        super(id);
        this.title = title;
    }

    public PlaceListData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}

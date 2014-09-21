/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.place.dto;

/**
 *
 * @author Ильдар
 */
public class NewPlaceData {
    private String title;

    public NewPlaceData() {
    }

    public NewPlaceData(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}

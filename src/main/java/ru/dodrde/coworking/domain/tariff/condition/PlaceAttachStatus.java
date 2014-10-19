/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.tariff.condition;

/**
 *
 * @author Ильдар
 */
public enum PlaceAttachStatus {
    
    ATTACH("Прикрепленный"),
    FLUID("Плавающий");
    
    private final String title;
    
    PlaceAttachStatus(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

}

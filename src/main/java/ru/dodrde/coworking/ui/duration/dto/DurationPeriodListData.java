/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.duration.dto;

/**
 *
 * @author Ильдар
 */
public class DurationPeriodListData {
    private String title;
    private String name;

    public DurationPeriodListData(String title, String name) {
        this.title = title;
        this.name = name;
    }

    public DurationPeriodListData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}

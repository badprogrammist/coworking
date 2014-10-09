/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.option.dto;

import ru.dodrde.coworking.ui.DTO;

/**
 *
 * @author Ильдар
 */
public class ViewOptionData extends DTO {
    
    private String title;
    private String description;

    public ViewOptionData() {
    }

    public ViewOptionData(Long id, String title, String description) {
        super(id);
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}

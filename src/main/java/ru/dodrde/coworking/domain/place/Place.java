/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.place;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.dodrde.coworking.domain.AbstractEntity;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "places")
@NamedQueries({
    @NamedQuery(name = "Place.findAll",
            query = "Select c from Place  c")
})
public class Place extends AbstractEntity {
    
    @Column(name="title")
    private String title;
    
    @Column(name="capacity")
    private Integer capacity = 1;

    public Place() {
    }
    
    public Place(String title,Integer capacity) {
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
        if(capacity == null) capacity = 1;
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}

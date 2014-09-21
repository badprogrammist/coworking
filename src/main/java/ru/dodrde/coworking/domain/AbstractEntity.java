/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Ильдар
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    public AbstractEntity() {
    }

    
    public Long getId() {
        return id;
    }
    
    
}

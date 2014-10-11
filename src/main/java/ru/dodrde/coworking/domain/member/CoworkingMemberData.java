/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.member;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.eclipse.persistence.sdo.types.SDOWrapperType;

/**
 *
 * @author Ильдар
 */
@Embeddable
public class CoworkingMemberData implements Serializable {
    
    @Column(name="name")
    private String name;
    
    @Column(name="lastname")
    private String lastname;
    
    @Column(name="patronymic")
    private String patronymic;

    public CoworkingMemberData() {
    }

    public CoworkingMemberData(String name, String lastname, String patronymic) {
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
    }

    public String getFullname() {
        StringBuilder builder = new StringBuilder(name);
        if(lastname != null && !lastname.isEmpty()) {
            builder.append(" ").append(lastname);
        }
        if(patronymic != null && !patronymic.isEmpty()) {
            builder.append(" ").append(patronymic);
        }
        return builder.toString();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    
    
}

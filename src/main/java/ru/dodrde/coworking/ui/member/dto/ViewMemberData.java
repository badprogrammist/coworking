/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.member.dto;

import ru.dodrde.coworking.ui.DTO;


/**
 *
 * @author Ильдар
 */
public class ViewMemberData extends DTO {
    private String name;
    private String lastname;
    private String patronymic;

    public ViewMemberData() {
    }

    public ViewMemberData(Long id, String name, String lastname, String patronymic) {
        super(id);
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
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

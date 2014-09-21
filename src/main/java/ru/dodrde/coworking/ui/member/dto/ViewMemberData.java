/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.ui.member.dto;


/**
 *
 * @author Ильдар
 */
public class ViewMemberData {
    private long id;
    private String name;
    private String lastname;
    private String patronymic;

    public ViewMemberData() {
    }

    public ViewMemberData(long id, String name, String lastname, String patronymic) {
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
}

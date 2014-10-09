/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.tariff;

/**
 *
 * @author Ильдар
 */
public enum DurationPeriod {
    
    DAY("День"),
    WEEK("Неделя"),
    MONTH("Месяц"),
    YEAR("Год");
    
    private final String title;
    
    DurationPeriod(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    
}

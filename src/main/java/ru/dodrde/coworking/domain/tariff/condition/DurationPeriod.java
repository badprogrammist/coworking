/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.tariff.condition;

import java.util.Calendar;

/**
 *
 * @author Ильдар
 */
public enum DurationPeriod {
    
    DAY("День",Calendar.DAY_OF_MONTH),
    WEEK("Неделя",Calendar.WEEK_OF_MONTH),
    MONTH("Месяц",Calendar.MONTH),
    YEAR("Год",Calendar.YEAR);
    
    private final String title;
    private final int calendarConstant;
    
    DurationPeriod(String title,int calendarConstant) {
        this.title = title;
        this.calendarConstant = calendarConstant;
    }
    

    public String getTitle() {
        return title;
    }

    public int getCalendarConstant() {
        return calendarConstant;
    }
    
}

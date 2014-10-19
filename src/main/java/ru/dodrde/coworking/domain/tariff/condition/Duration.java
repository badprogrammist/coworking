/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.tariff.condition;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Ильдар
 */
@Embeddable
public class Duration implements Serializable {
    
    @Column(name = "period_quantity")
    private Integer periodQuantity = 1;
    
    @Enumerated(EnumType.STRING)
    @Column(name="duration_period")
    private DurationPeriod period = DurationPeriod.DAY;

    public Duration() {
    }

    public Duration(DurationPeriod period, Integer periodQuantity) {
        this.period = period;
        this.periodQuantity = periodQuantity;
    }

    public Integer getPeriodQuantity() {
        return periodQuantity;
    }

    public void setPeriodQuantity(Integer periodQuantity) {
        this.periodQuantity = periodQuantity;
    }

    public DurationPeriod getPeriod() {
        return period;
    }

    public void setPeriod(DurationPeriod period) {
        this.period = period;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.periodQuantity);
        hash = 89 * hash + Objects.hashCode(this.period);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Duration other = (Duration) obj;
        if (!Objects.equals(this.periodQuantity, other.periodQuantity)) {
            return false;
        }
        return this.period == other.period;
    }
    
    
    
}

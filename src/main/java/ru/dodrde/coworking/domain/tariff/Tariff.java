/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dodrde.coworking.domain.tariff;

import ru.dodrde.coworking.domain.tariff.condition.PlaceAttachStatus;
import ru.dodrde.coworking.domain.tariff.condition.Duration;
import java.math.BigDecimal;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.dodrde.coworking.domain.tariff.condition.Condition;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "tariffs")
@NamedQueries({
    @NamedQuery(name = "Tariff.findAll",
            query = "Select c from Tariff c"),
    @NamedQuery(name = "Tariff.findByDuration",
            query = "Select c from Tariff c WHERE c.duration = :duration")
})
public class Tariff extends Condition<TariffOptionRelation> {

    @Embedded
    private TariffDescription description = new TariffDescription();
    
    
    public Tariff() {
    }

    public Tariff(TariffDescription description,Duration duration, BigDecimal price, PlaceAttachStatus placeAttachStatus) {
        super(duration, price, placeAttachStatus);
    }

    public TariffDescription getDescription() {
        if(description == null) {
            description = new TariffDescription();
        }
        return description;
    }

    public void setDescription(TariffDescription description) {
        this.description = description;
    }

}

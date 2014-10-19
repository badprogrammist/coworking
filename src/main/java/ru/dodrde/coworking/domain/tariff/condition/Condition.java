/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.tariff.condition;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import ru.dodrde.coworking.domain.AbstractEntity;

/**
 *
 * @author Ильдар
 * @param <O>
 */
@MappedSuperclass
public abstract class Condition<O extends ConditionOptionRelation> extends AbstractEntity {
    
    @Embedded
    private Duration duration = new Duration();
    
    @Column(name="price")
    private BigDecimal price;
    
    @Column(name="place_attach_status")
    @Enumerated(EnumType.STRING)
    private PlaceAttachStatus placeAttachStatus = PlaceAttachStatus.FLUID;
    
    @OneToMany(mappedBy = "condition",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    private List<O> optionRelations = new ArrayList<>();
    
    public Condition(Duration duration, BigDecimal price, PlaceAttachStatus placeAttachStatus) {
        this.duration = duration;
        this.price = price;
        this.placeAttachStatus = placeAttachStatus;
    }

    public Condition() {
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PlaceAttachStatus getPlaceAttachStatus() {
        if(placeAttachStatus == null) {
            placeAttachStatus = PlaceAttachStatus.FLUID;
        }
        return placeAttachStatus;
    }

    public void setPlaceAttachStatus(PlaceAttachStatus placeAttachStatus) {
        this.placeAttachStatus = placeAttachStatus;
    }

    public List<O> getOptionRelations() {
        return optionRelations;
    }

    public void setOptionRelations(List<O> optionRelations) {
        this.optionRelations = optionRelations;
    }
    
    
}

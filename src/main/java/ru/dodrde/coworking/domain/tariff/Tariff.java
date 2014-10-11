/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dodrde.coworking.domain.tariff;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ru.dodrde.coworking.domain.AbstractEntity;

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
public class Tariff extends AbstractEntity {

    @Embedded
    private TariffDescription description = new TariffDescription();
    
    @Embedded
    private Duration duration = new Duration();
    
    @Column(name="price")
    private BigDecimal price;
    
    @OneToMany(mappedBy = "tariff",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TariffOptionRelation> optionRelations = new ArrayList<>();

    public Tariff() {
    }

    public Tariff(TariffDescription description,Duration duration, BigDecimal price) {
        this.description = description;
        this.duration = duration;
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal total = new BigDecimal(0.0);
        total = total.add(price);
        for(TariffOptionRelation optionRelation : optionRelations) {
            total = total.add(optionRelation.getOptionPrice().getPrice());
        }
        return total;
    }
    
    public TariffDescription getDescription() {
        return description;
    }

    public void setDescription(TariffDescription description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<TariffOptionRelation> getOptionRelations() {
        return optionRelations;
    }

    public void setOptionRelations(List<TariffOptionRelation> optionRelations) {
        this.optionRelations = optionRelations;
    }

}

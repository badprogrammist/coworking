/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.tariff;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.dodrde.coworking.domain.AbstractEntity;
import ru.dodrde.coworking.domain.option.Option;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "tariff_option_relations")
@NamedQueries({
    @NamedQuery(name = "TariffOptionRelation.findAll",
            query = "Select c from TariffOptionRelation c"),
    @NamedQuery(name = "TariffOptionRelation.findByOption",
            query = "Select c from TariffOptionRelation c WHERE c.option = :option"),
    @NamedQuery(name = "TariffOptionRelation.findByTariff",
            query = "Select c from TariffOptionRelation c WHERE c.tariff = :tariff")
})
public class TariffOptionRelation extends AbstractEntity {
    
    @JoinColumn(name = "tariff_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tariff tariff;
    
    @JoinColumn(name = "option_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;

    public TariffOptionRelation() {
    }

    public TariffOptionRelation(Tariff tariff, Option option) {
        this.tariff = tariff;
        this.option = option;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
    
    
}

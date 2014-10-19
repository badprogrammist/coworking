/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.tariff;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.dodrde.coworking.domain.option.Option;
import ru.dodrde.coworking.domain.tariff.condition.ConditionOptionRelation;

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
            query = "Select c from TariffOptionRelation c WHERE c.condition = :condition")
})
public class TariffOptionRelation extends ConditionOptionRelation<Tariff> {
    

    public TariffOptionRelation() {
    }

    public TariffOptionRelation(Tariff tariff, Option option) {
        super(tariff, option);
    }

}

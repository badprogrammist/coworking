/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.tariff.condition;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import ru.dodrde.coworking.domain.AbstractEntity;
import ru.dodrde.coworking.domain.option.Option;

/**
 *
 * @author Ильдар
 * @param <C>
 */
@MappedSuperclass
public abstract class ConditionOptionRelation<C extends Condition> extends AbstractEntity {
    
    @JoinColumn(name = "condition_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private C condition;
    
    @JoinColumn(name = "option_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;

    public ConditionOptionRelation() {
    }

    public ConditionOptionRelation(C condition, Option option) {
        this.condition = condition;
        this.option = option;
    }

    public C getCondition() {
        return condition;
    }

    public void setCondition(C condition) {
        this.condition = condition;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.tariff;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import ru.dodrde.coworking.domain.option.Option;

/**
 *
 * @author Ильдар
 */
@Embeddable
public class OptionPrice implements Serializable {
    
    @JoinColumn(name = "option_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;
    
    @Column(name="price")
    private BigDecimal price;

    public OptionPrice() {
    }

    public OptionPrice(Option option, BigDecimal price) {
        this.option = option;
        this.price = price;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public BigDecimal getPrice() {
        if(price == null) {
            price = new BigDecimal(0.0);
        }
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    
    
}

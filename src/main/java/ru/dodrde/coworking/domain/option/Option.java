/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.option;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.dodrde.coworking.domain.AbstractEntity;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "options")
@NamedQueries({
    @NamedQuery(name = "Option.findAll",
            query = "Select c from Option  c")
})
public class Option extends AbstractEntity {
    
    @Embedded
    private OptionDescription description = new OptionDescription();

    public Option() {
    }

    public Option(OptionDescription description) {
        this.description = description;
    }

    public OptionDescription getDescription() {
        return description;
    }

    public void setDescription(OptionDescription description) {
        this.description = description;
    }
    
    
}

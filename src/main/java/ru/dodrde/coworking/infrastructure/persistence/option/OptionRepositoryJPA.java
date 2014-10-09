/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.infrastructure.persistence.option;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.dodrde.coworking.domain.option.Option;
import ru.dodrde.coworking.domain.option.OptionRepository;
import ru.dodrde.coworking.infrastructure.persistence.AbstractRepositoryJPA;

/**
 *
 * @author Ильдар
 */
@Repository
public class OptionRepositoryJPA extends AbstractRepositoryJPA<Option> implements OptionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public OptionRepositoryJPA() {
        super(Option.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
}

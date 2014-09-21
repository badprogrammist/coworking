/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ru.dodrde.coworking.domain.AbstractEntity;
import ru.dodrde.coworking.domain.EntityRepository;

/**
 *
 * @author Ильдар
 * @param <E>
 */
@Transactional
public abstract class AbstractService<E extends AbstractEntity> {
    
    protected abstract EntityRepository getRepository();
    
    protected void store(E entity) {
        getRepository().store(entity);
    }
    
    protected void update(E entity) {
        getRepository().update(entity);
    }
    
    public List<E> getAll() {
        return getRepository().getAll();
    }
    
    public E get(long id) {
        return (E)getRepository().get(id);
    }
    
    public void remove(long id) {
        E entity = get(id);
        if(entity != null) {
            remove(entity);
        }
    }
    
    public void remove(E entity) {
        getRepository().remove(entity);
    }
    
}

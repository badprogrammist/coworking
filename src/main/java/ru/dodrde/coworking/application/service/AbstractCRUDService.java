/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ru.dodrde.coworking.application.CRUDService;
import ru.dodrde.coworking.domain.AbstractEntity;
import ru.dodrde.coworking.domain.EntityRepository;

/**
 *
 * @author Ильдар
 * @param <E>
 */
@Transactional
public abstract class AbstractCRUDService<E extends AbstractEntity> implements CRUDService<E> {
    
    protected abstract EntityRepository getRepository();
    
    @Override
    public void update(E entity) {
        getRepository().update(entity);
    }
    
    @Override
    public List<E> getAll() {
        return getRepository().getAll();
    }
    
    @Override
    public E get(Long id) {
        return (E)getRepository().get(id);
    }
    
    @Override
    public void remove(Long id) {
        E entity = get(id);
        if(entity != null) {
            remove(entity);
        }
    }
    
    @Override
    public void remove(E entity) {
        getRepository().remove(entity);
    }
    
}

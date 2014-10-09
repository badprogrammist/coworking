/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application;

import java.util.List;
import ru.dodrde.coworking.domain.AbstractEntity;

/**
 *
 * @author Ильдар
 * @param <E>
 */
public interface CRUDService<E extends AbstractEntity> {
    
    public void update(E entity);
    
    public List<E> getAll();
    
    public E get(Long id);
    
    public void remove(Long id);
    
    public void remove(E entity);
}

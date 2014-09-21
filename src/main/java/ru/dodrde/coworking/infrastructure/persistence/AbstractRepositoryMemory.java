/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.infrastructure.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.dodrde.coworking.domain.AbstractEntity;
import ru.dodrde.coworking.domain.EntityRepository;

/**
 *
 * @author Ильдар
 * @param <E>
 */
public class AbstractRepositoryMemory<E extends AbstractEntity> implements EntityRepository<E> {

    private final Map<Long, E> holder = new HashMap<>();
    private long nextId = 0;
    
    @Override
    public void store(E entity) {
        holder.put(nextId++, entity);
    }

    @Override
    public void update(E entity) {
        holder.put(entity.getId(), entity);
    }

    

    @Override
    public E get(Long id) {
        return holder.get(id);
    }

    @Override
    public List<E> getAll() {
        return new ArrayList<>(holder.values());
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
        holder.remove(entity.getId());
    }
    
}

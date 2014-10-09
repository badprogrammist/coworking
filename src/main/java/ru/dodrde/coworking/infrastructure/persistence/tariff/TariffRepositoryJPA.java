/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.infrastructure.persistence.tariff;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.dodrde.coworking.domain.tariff.Duration;
import ru.dodrde.coworking.domain.tariff.Tariff;
import ru.dodrde.coworking.domain.tariff.TariffRepository;
import ru.dodrde.coworking.infrastructure.persistence.AbstractRepositoryJPA;

/**
 *
 * @author Ильдар
 */
@Repository
public class TariffRepositoryJPA extends AbstractRepositoryJPA<Tariff> implements TariffRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TariffRepositoryJPA() {
        super(Tariff.class);
    }
    
    

    @Override
    public List<Tariff> findByDuration(Duration duration) {
        return getEntityManager().createNamedQuery("Tariff.findByDuration", Tariff.class)
                .setParameter("duration", duration)
                .getResultList();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
}

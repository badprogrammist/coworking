/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.tariff;

import ru.dodrde.coworking.domain.tariff.condition.Duration;
import java.util.List;
import ru.dodrde.coworking.domain.EntityRepository;

/**
 *
 * @author Ильдар
 */
public interface TariffRepository extends EntityRepository<Tariff> {
    public List<Tariff> findByDuration(Duration duration);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application;

import java.math.BigDecimal;
import java.util.List;
import ru.dodrde.coworking.domain.option.Option;
import ru.dodrde.coworking.domain.tariff.Duration;
import ru.dodrde.coworking.domain.tariff.Tariff;
import ru.dodrde.coworking.domain.tariff.TariffDescription;

/**
 *
 * @author Ильдар
 */
public interface TariffService extends CRUDService<Tariff> {
    public void createTariff(TariffDescription description, Duration duration, BigDecimal price, List<Option> options);
    public void updateTariff(Tariff tariff, List<Option> options);
    public void attachOption(Tariff tariff,Option option);
    public void detachOption(Tariff tariff,Option option);
    public List<Tariff> getRelevantTariff(Duration duration);
}

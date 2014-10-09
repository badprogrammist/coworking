/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application;

import ru.dodrde.coworking.domain.option.Option;
import ru.dodrde.coworking.domain.option.OptionDescription;

/**
 *
 * @author Ильдар
 */
public interface OptionService extends CRUDService<Option> {
    public void createOption(OptionDescription description);
}

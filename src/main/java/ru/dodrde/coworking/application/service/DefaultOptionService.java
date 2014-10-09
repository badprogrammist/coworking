/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dodrde.coworking.application.OptionService;
import ru.dodrde.coworking.domain.EntityRepository;
import ru.dodrde.coworking.domain.option.Option;
import ru.dodrde.coworking.domain.option.OptionDescription;
import ru.dodrde.coworking.domain.option.OptionRepository;

/**
 *
 * @author Ильдар
 */
@Service
@Transactional
public class DefaultOptionService extends AbstractCRUDService<Option> implements OptionService {

    @Inject
    private OptionRepository optionRepository;

    @Override
    public void createOption(OptionDescription description) {
        Option option = new Option(description);
        optionRepository.store(option);
    }
    
    @Override
    protected EntityRepository getRepository() {
        return optionRepository;
    }
    
}

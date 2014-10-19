/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dodrde.coworking.application.UserRegistrationService;
import ru.dodrde.coworking.domain.AbstractEntity;
import ru.dodrde.coworking.domain.EntityRepository;
import ru.dodrde.coworking.domain.user.User;
import ru.dodrde.coworking.domain.user.UserDescription;
import ru.dodrde.coworking.domain.user.UserRepository;

/**
 *
 * @author Ильдар
 */
@Service
@Transactional
public class DefaultUserRegistrationService extends AbstractCRUDService<User> implements UserRegistrationService {

    @Inject
    private UserRepository memberRepository;
    
    
    @Override
    public void registerMember(UserDescription memberData) {
        User member = new User(memberData);
        memberRepository.store(member);
    }

    @Override
    protected EntityRepository getRepository() {
        return memberRepository;
    }
}

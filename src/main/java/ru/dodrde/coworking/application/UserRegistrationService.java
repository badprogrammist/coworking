/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application;

import ru.dodrde.coworking.domain.user.User;
import ru.dodrde.coworking.domain.user.UserDescription;

/**
 *
 * @author Ильдар
 */
public interface UserRegistrationService extends CRUDService<User> {
    public void registerMember(UserDescription memberData);
}
 
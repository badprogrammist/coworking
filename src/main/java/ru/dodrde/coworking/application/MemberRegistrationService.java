/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application;

import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.member.CoworkingMemberData;

/**
 *
 * @author Ильдар
 */
public interface MemberRegistrationService extends CRUDService<CoworkingMember> {
    public void registerMember(CoworkingMemberData memberData);
}
 
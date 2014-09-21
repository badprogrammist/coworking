/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.application;

import java.util.List;
import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.member.CoworkingMemberData;

/**
 *
 * @author Ильдар
 */
public interface MemberRegistrationService {
    
    public void registerNewMember(CoworkingMemberData memberData);
    public List<CoworkingMember> getCoworkingMembers();
    public CoworkingMember getCoworkingMember(Long id);
    public void removeCoworkingMember(Long id);
    public void updateCoworkingMember(CoworkingMember member);
}
 
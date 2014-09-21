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
import ru.dodrde.coworking.application.MemberRegistrationService;
import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.member.CoworkingMemberData;
import ru.dodrde.coworking.domain.member.MemberRepository;

/**
 *
 * @author Ильдар
 */
@Service
@Transactional
public class DefaultRegistrationService implements MemberRegistrationService {

    @Inject
    private MemberRepository memberRepository;
    
    
    @Override
    public void registerMember(CoworkingMemberData memberData) {
        CoworkingMember member = new CoworkingMember(memberData);
        memberRepository.store(member);
    }

    @Override
    public List<CoworkingMember> getCoworkingMembers() {
        return memberRepository.getAll();
    }

    @Override
    public CoworkingMember getCoworkingMember(Long id) {
        return memberRepository.get(id);
    }

    @Override
    public void removeCoworkingMember(Long id) {
        memberRepository.remove(id);
    }

    @Override
    public void updateCoworkingMember(CoworkingMember member) {
        memberRepository.update(member);
    }
    
}

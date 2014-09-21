/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.infrastructure.persistence.member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.dodrde.coworking.domain.member.CoworkingMember;
import ru.dodrde.coworking.domain.member.MemberRepository;
import ru.dodrde.coworking.infrastructure.persistence.AbstractRepositoryJPA;

/**
 *
 * @author Ильдар
 */
@Repository
public class MemberRepositoryJPA extends AbstractRepositoryJPA<CoworkingMember> implements MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;
    
    public MemberRepositoryJPA() {
        super(CoworkingMember.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
}

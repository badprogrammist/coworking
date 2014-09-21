/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dodrde.coworking.domain.member;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.dodrde.coworking.domain.AbstractEntity;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "coworking_members")
@NamedQueries({
    @NamedQuery(name = "CoworkingMember.findAll",
            query = "Select c from CoworkingMember  c")
})
public class CoworkingMember extends AbstractEntity {
    
    @Embedded
    private CoworkingMemberData memberData;

    public CoworkingMember() {
    }

    public CoworkingMember(CoworkingMemberData memberData) {
        this.memberData = memberData;
    }

    public CoworkingMemberData getMemberData() {
        return memberData;
    }

    public void setMemberData(CoworkingMemberData memberData) {
        this.memberData = memberData;
    }

    
    
}

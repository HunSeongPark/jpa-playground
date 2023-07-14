package com.example.jpa_playground.persist_merge;

import com.example.jpa_playground.Member;
import com.example.jpa_playground.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Transactional
public class PersistMergeTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TestEntityManager tem;

    private EntityManager em;

    @BeforeEach
    void init() {
        this.em = tem.getEntityManager();
    }

    @Test
    void saveWithPersistAndMergeTest() {
        Member member1 = new Member("member", 10); // member1 (New)
        Member saveMember1 = memberRepository.save(member1); // Persist (New -> Persist)
        assertThat(em.contains(member1)).isTrue(); // member1(persist) : 관리됨
        assertThat(em.contains(saveMember1)).isTrue(); // saveMember1(persist 반환 Entity) : 관리됨

        Member member2 = new Member("member2", 20); // member2 (Detach)
        member2.setId(member1.getId());
        Member saveMember2 = memberRepository.save(member2); // Merge (Detach -> Persist)
        assertThat(em.contains(member2)).isFalse(); // member2(Detach) : 관리되지 않음
        assertThat(em.contains(saveMember2)).isTrue(); // saveMember2(merge 반환 Entity) : 관리됨
        assertThat(saveMember2.getName()).isEqualTo(member2.getName()); // merge 되어 값 변경됨
    }
}

package com.example.jpa_playground.dirty_checking.service;

import com.example.jpa_playground.dirty_checking.domain.Member;
import com.example.jpa_playground.dirty_checking.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(String name, Integer age) {
        Member member = new Member(name, age);
        memberRepository.save(member);
        return member.getId();
    }

    public void update(Long memberId) {

        // find member (in persistence context)
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("member not found"));

        // dirty checking
        member.setName("change name");
    }
}

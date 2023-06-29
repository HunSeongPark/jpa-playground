package com.example.jpa_playground.dirty_checking.controller;

import com.example.jpa_playground.dirty_checking.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/dirty-checking/member")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Long> save(@RequestParam String name, @RequestParam Integer age) {
        return ResponseEntity.ok(memberService.save(name, age));
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Void> update(@PathVariable Long memberId) {
        memberService.update(memberId);
        return ResponseEntity.noContent().build();
    }
}

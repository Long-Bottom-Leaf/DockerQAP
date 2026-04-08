package com.project.dockerapp.controllers;

import com.project.dockerapp.models.Member;
import com.project.dockerapp.models.MembershipType;
import com.project.dockerapp.repository.MemberRepository;
import com.project.dockerapp.services.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@Valid @RequestBody Member member) {
        return new ResponseEntity<>(
                memberService.createMember(member),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/firstname")
    public ResponseEntity<List<Member>> getByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(memberRepository.findByFirstNameIgnoreCase(firstName));
    }

    @GetMapping("/search/lastname")
    public ResponseEntity<List<Member>> getByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(memberRepository.findByLastNameIgnoreCase(lastName));
    }

    @GetMapping("/search/type")
    public ResponseEntity<List<Member>> getByMembershipType(@RequestParam MembershipType type) {
        return ResponseEntity.ok(memberRepository.findByMembershipType(type));
    }

    @GetMapping("/search/phone")
    public ResponseEntity<List<Member>> getByPhone(@RequestParam String phone) {
        return ResponseEntity.ok(memberRepository.findByPhoneNumber(phone));
    }

    @GetMapping("/search/tournament-date")
    public ResponseEntity<List<Member>> getByTournamentDate(@RequestParam LocalDate date) {
        return ResponseEntity.ok(memberRepository.findByTournaments_StartDate(date));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(
            @PathVariable Long id,
            @RequestBody Member updatedMember
    ) {
        Member existing = memberService.getMemberById(id);

        existing.setFirstName(updatedMember.getFirstName());
        existing.setLastName(updatedMember.getLastName());
        existing.setEmail(updatedMember.getEmail());
        existing.setPhoneNumber(updatedMember.getPhoneNumber());
        existing.setMembershipType(updatedMember.getMembershipType());
        existing.setStartDate(updatedMember.getStartDate());
        existing.setDuration(updatedMember.getDuration());

        return ResponseEntity.ok(memberRepository.save(existing));
    }
}
package com.project.dockerapp.services;

import com.project.dockerapp.exception.BadRequestException;
import com.project.dockerapp.exception.ResourceNotFoundException;
import com.project.dockerapp.models.Member;
import com.project.dockerapp.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {

        if (member.getEmail() == null || member.getEmail().isBlank()) {
            throw new BadRequestException("Email is required");
        }

        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
    }

    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }
}
package com.project.dockerapp.servicesTest;

import com.project.dockerapp.exception.BadRequestException;
import com.project.dockerapp.exception.ResourceNotFoundException;
import com.project.dockerapp.models.Member;
import com.project.dockerapp.models.MembershipType;
import com.project.dockerapp.repository.MemberRepository;
import com.project.dockerapp.services.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void createMember_success() {
        Member member = new Member(
                null,
                "John",
                "Doe",
                "john@test.com",
                "1234567890",
                MembershipType.BASIC_MEMBER,
                LocalDate.now(),
                12,
                null
        );

        when(memberRepository.existsByEmail(anyString())).thenReturn(false);
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Member saved = memberService.createMember(member);

        assertNotNull(saved);
        assertEquals("john@test.com", saved.getEmail());
    }

    @Test
    void createMember_duplicateEmail_throwsException() {
        Member member = new Member();
        member.setEmail("test@email.com");

        when(memberRepository.existsByEmail(member.getEmail())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> {
            memberService.createMember(member);
        });

        verify(memberRepository, never()).save(any());
    }

    @Test
    void getMemberById_success() {
        Member member = new Member();
        member.setId(1L);

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        Member result = memberService.getMemberById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void getMemberById_notFound() {
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            memberService.getMemberById(1L);
        });
    }

    @Test
    void deleteMember_notFound() {
        when(memberRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            memberService.deleteMember(1L);
        });
    }
}
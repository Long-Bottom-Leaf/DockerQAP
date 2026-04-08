package com.project.dockerapp.servicesTest;

import com.project.dockerapp.exception.BadRequestException;
import com.project.dockerapp.exception.ResourceNotFoundException;
import com.project.dockerapp.models.Member;
import com.project.dockerapp.models.Tournament;
import com.project.dockerapp.repository.MemberRepository;
import com.project.dockerapp.repository.TournamentRepository;
import com.project.dockerapp.services.TournamentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TournamentServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private TournamentService tournamentService;

    @Test
    void createTournament_invalidDates() {
        Tournament t = new Tournament();
        t.setStartDate(LocalDate.now());
        t.setEndDate(LocalDate.now().minusDays(1));

        assertThrows(BadRequestException.class, () -> {
            tournamentService.createTournament(t);
        });
    }

    @Test
    void addMemberToTournament_success() {
        Tournament t = new Tournament();
        t.setId(1L);
        t.setParticipatingMembers(new ArrayList<>());

        Member m = new Member();
        m.setId(2L);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(t));
        when(memberRepository.findById(2L)).thenReturn(Optional.of(m));
        when(tournamentRepository.save(t)).thenReturn(t);

        Tournament result = tournamentService.addMemberToTournament(1L, 2L);

        assertTrue(result.getParticipatingMembers().contains(m));
    }

    @Test
    void addMemberToTournament_duplicateMember() {
        Member m = new Member();
        m.setId(2L);

        Tournament t = new Tournament();
        t.setParticipatingMembers(new ArrayList<>());
        t.getParticipatingMembers().add(m);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(t));
        when(memberRepository.findById(2L)).thenReturn(Optional.of(m));

        assertThrows(BadRequestException.class, () -> {
            tournamentService.addMemberToTournament(1L, 2L);
        });
    }

    @Test
    void addMemberToTournament_notFound() {
        when(tournamentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            tournamentService.addMemberToTournament(1L, 2L);
        });
    }
}
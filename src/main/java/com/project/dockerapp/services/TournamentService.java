package com.project.dockerapp.services;

import com.project.dockerapp.exception.BadRequestException;
import com.project.dockerapp.exception.ResourceNotFoundException;
import com.project.dockerapp.models.Member;
import com.project.dockerapp.models.Tournament;
import com.project.dockerapp.repository.MemberRepository;
import com.project.dockerapp.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final MemberRepository memberRepository;

    public TournamentService(TournamentRepository tournamentRepository,
                             MemberRepository memberRepository) {
        this.tournamentRepository = tournamentRepository;
        this.memberRepository = memberRepository;
    }

    public Tournament createTournament(Tournament tournament) {

        if (tournament.getEndDate().isBefore(tournament.getStartDate())) {
            throw new BadRequestException("End date cannot be before start date");
        }

        return tournamentRepository.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found with id: " + id));
    }

    public void deleteTournament(Long id) {
        if (!tournamentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tournament not found with id: " + id);
        }
        tournamentRepository.deleteById(id);
    }

    public Tournament addMemberToTournament(Long tournamentId, Long memberId) {

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        if (tournament.getParticipatingMembers().contains(member)) {
            throw new BadRequestException("Member already in tournament");
        }

        tournament.getParticipatingMembers().add(member);

        return tournamentRepository.save(tournament);
    }
}
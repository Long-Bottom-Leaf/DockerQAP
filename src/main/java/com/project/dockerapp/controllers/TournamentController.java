package com.project.dockerapp.controllers;

import com.project.dockerapp.models.Tournament;
import com.project.dockerapp.repository.TournamentRepository;
import com.project.dockerapp.services.TournamentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {
    private final TournamentService tournamentService;
    private final TournamentRepository tournamentRepository;

    public TournamentController(TournamentService tournamentService, TournamentRepository tournamentRepository) {
        this.tournamentService = tournamentService;
        this.tournamentRepository = tournamentRepository;
    }

    @PostMapping
    public ResponseEntity<Tournament> createTournament(@Valid @RequestBody Tournament tournament) {
        return new ResponseEntity<>(
                tournamentService.createTournament(tournament),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        return ResponseEntity.ok(tournamentService.getAllTournaments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        return ResponseEntity.ok(tournamentService.getTournamentById((id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{tournamentId}/members/{memberId}")
    public ResponseEntity<Tournament> addMemberToTournament(
            @PathVariable Long tournamentId,
            @PathVariable Long memberId
        ) {
            return ResponseEntity.ok(
                    tournamentService.addMemberToTournament(tournamentId, memberId)
            );
    }

    @GetMapping("/search/location")
    public ResponseEntity<List<Tournament>> getByLocation(@RequestParam String location) {
        return ResponseEntity.ok(tournamentRepository.findByLocation(location));
    }

    @GetMapping("/search/member")
    public ResponseEntity<List<Tournament>> getByMember(@RequestParam Long memberId) {
        return ResponseEntity.ok(tournamentRepository.findByParticipatingMembers_Id(memberId));
    }

    @GetMapping("/search/date")
    public ResponseEntity<List<Tournament>> getByStartDate(@RequestParam LocalDate date) {
        return ResponseEntity.ok(tournamentRepository.findByStartDate(date));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tournament> updateTournament(
            @PathVariable Long id,
            @RequestBody Tournament updatedTournament
    ) {
        Tournament existing = tournamentService.getTournamentById(id);

        existing.setStartDate(updatedTournament.getStartDate());
        existing.setEndDate(updatedTournament.getEndDate());
        existing.setLocation(updatedTournament.getLocation());
        existing.setEntryFee(updatedTournament.getEntryFee());
        existing.setCashPrize(updatedTournament.getCashPrize());

        return ResponseEntity.ok(tournamentRepository.save(existing));
    }
}

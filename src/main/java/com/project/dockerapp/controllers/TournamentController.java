package com.project.dockerapp.controllers;

import com.project.dockerapp.models.Tournament;
import com.project.dockerapp.services.TournamentService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {
    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
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
}

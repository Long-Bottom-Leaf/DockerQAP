package com.project.dockerapp.repository;

import com.project.dockerapp.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    List<Tournament> findByStartDate(LocalDate startDate);

    List<Tournament> findByLocation(String location);

    List<Tournament> findByParticipatingMembers_Id(Long memberId);
}
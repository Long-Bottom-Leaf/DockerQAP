package com.project.dockerapp.repository;

import com.project.dockerapp.models.Member;
import com.project.dockerapp.models.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    List<Member> findByFirstNameIgnoreCase(String firstName);

    List<Member> findByLastNameIgnoreCase(String lastName);

    List<Member> findByMembershipType(MembershipType membershipType);

    List<Member> findByPhoneNumber(String phoneNumber);

    List<Member> findByTournaments_StartDate(LocalDate startDate);
}
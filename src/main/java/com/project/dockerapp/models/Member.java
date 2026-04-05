package com.project.dockerapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

    private LocalDate startDate;
    private int duration;

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    private List<Tournament> tournaments;
}

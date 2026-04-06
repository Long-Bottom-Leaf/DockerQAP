package com.project.dockerapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @NotBlank
    private String firstName;

    @Column(nullable = false)
    @NotBlank
    private String lastName;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Email
    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

    private LocalDate startDate;
    private int duration;

    @JsonIgnore
    @ManyToMany(mappedBy = "participatingMembers", fetch = FetchType.LAZY)
    private List<Tournament> tournaments = new ArrayList<>();
}

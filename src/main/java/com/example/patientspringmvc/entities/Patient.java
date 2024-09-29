package com.example.patientspringmvc.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;


@Entity
@Table(name = "patients")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 4, max = 20)
    private String nom;
    @NotEmpty @Size(min = 4, max = 20)
    private String prenom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Min(10)
    private int score;
    private boolean malade;
}

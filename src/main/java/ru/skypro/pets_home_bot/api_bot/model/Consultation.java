package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "consultations")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type_consultation")
    private String typeConsultation;

    @Column(columnDefinition = "TEXT")
    private String definition;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;
}

package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Data
@Table(name = "shelter_info")
public class ShelterInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type_info")
    private String typeInfo;

    @Column(columnDefinition = "TEXT")
    private String definition;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;
}

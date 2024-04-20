package ru.skypro.pets_home_bot.api_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "schemes")
public class ShelterScheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @JsonIgnore
    private byte[] scheme;

    @OneToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;
}

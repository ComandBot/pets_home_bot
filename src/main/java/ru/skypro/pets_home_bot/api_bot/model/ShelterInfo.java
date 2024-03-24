package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.Data;

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

    @Lob
    private byte[] scheme;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;
}

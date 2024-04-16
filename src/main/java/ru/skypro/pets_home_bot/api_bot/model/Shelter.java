package ru.skypro.pets_home_bot.api_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ru.skypro.pets_home_bot.api_bot.enums.PetsTypes;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "shelters")
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_shelter")
    private String nameShelter;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "pet_type")
    @Enumerated(EnumType.STRING)
    private PetsTypes petsTypes;

    @JsonIgnore
    @OneToMany(mappedBy = "shelter", fetch = FetchType.LAZY)
    Collection<ShelterInfo> shelterInfos;

    @JsonIgnore
    @OneToMany(mappedBy = "shelter", fetch = FetchType.LAZY)
    Collection<Consultation> consultations;
}

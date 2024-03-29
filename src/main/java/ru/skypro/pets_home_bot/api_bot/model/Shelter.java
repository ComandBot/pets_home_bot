package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.skypro.pets_home_bot.api_bot.enums.PetsTypes;
import java.util.List;

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

    @OneToMany(mappedBy = "shelter")
    private List<Pet> pets;

    @OneToMany(mappedBy = "shelter")
    private List<Consultation> consultations;

    @OneToMany(mappedBy = "shelter")
    private List<ShelterInfo> shelterInfos;
}

package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.skypro.pets_home_bot.api_bot.enums.PetsTypes;

import java.util.List;

@Entity
@Data
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pet_type")
    @Enumerated(EnumType.STRING)
    private PetsTypes petsTypes;

    @Column(name = "pet_name")
    private String name;

    @OneToOne(mappedBy = "petUserId.pet")
    private Owner owner;
}

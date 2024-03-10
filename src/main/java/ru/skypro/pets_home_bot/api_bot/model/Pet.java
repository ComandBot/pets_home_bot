package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pet_name")
    private String name;

    @OneToOne(mappedBy = "ownerId.pet")
    private Owner owner;

    @OneToOne(mappedBy = "pet")
    private AvatarPet avatarPet;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;
}

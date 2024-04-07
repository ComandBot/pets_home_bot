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

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;
}

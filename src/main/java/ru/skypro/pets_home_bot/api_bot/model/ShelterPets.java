package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "shelter_pets")
public class ShelterPets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "pet_user_id")
    private PetsUser petsUser;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @CreationTimestamp
    private LocalDateTime dateDelivery;
}

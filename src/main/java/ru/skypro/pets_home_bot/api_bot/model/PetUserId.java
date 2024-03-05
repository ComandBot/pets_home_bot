package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class PetUserId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "pet_user_id")
    private PetUser petUser;

    @ManyToOne
    @JoinColumn(name = "pet_id", unique = true)
    private Pet pet;
}

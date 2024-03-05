package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "chat_id_pet_user")
    private int chatIdPetUser;

    @Column(name = "pet_id")
    private int petId;

    @CreationTimestamp
    private LocalDateTime dateDelivery;
}

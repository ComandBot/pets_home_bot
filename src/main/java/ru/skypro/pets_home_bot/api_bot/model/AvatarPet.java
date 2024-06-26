package ru.skypro.pets_home_bot.api_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pets_avatars")
public class AvatarPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob
    @JsonIgnore
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}

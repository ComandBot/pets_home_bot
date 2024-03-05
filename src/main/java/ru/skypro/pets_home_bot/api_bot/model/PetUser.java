package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@Table(name = "pets_user")
public class PetUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "chat_id")
    private int chatId;

    @OneToMany(mappedBy = "petUserId.petUser", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Owner> owner;

}

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

    @OneToMany(mappedBy = "ownerId.petUser", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Owner> owners;

    @OneToOne(mappedBy = "workUserId")
    private Volunteer volunteer;

    @OneToMany(mappedBy = "petUser")
    private List<Contact> contacts;

}

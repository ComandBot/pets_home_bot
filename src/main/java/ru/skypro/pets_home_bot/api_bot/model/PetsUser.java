package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@Table(name = "users")
public class PetsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "chat_id")
    private int chatId;

    @ManyToMany
    @JoinTable(
            name = "users_volunteer",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "volunteer_id"))
    private List<Volunteer> volunteers;
}

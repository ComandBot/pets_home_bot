package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.*;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;

import java.util.List;


@Entity
@Data
@Table(name = "pets_user")
public class PetUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "message_mode")
    @Enumerated(EnumType.STRING)
    private MessageMode messageMode;

    @OneToOne(mappedBy = "workUserId")
    private Volunteer volunteer;
}

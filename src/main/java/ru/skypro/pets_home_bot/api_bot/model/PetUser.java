package ru.skypro.pets_home_bot.api_bot.model;

import com.pengrad.telegrambot.model.User;
import jakarta.persistence.*;
import lombok.*;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;



@Entity
@Data
@Table(name = "pets_user")
public class PetUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "message_mode")
    @Enumerated(EnumType.STRING)
    private MessageMode messageMode;
}

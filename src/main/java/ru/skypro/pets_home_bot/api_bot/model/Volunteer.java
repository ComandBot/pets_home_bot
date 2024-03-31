package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;

@Entity
@Data
@Table(name = "volunteers")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "message_mode")
    @Enumerated(EnumType.STRING)
    private MessageMode messageMode;

    @OneToOne
    @JoinColumn(name = "work_user_id")
    private PetUser workUserId;
}

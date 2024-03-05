package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "owners")
public class Owner {

    @EmbeddedId
    PetUserId petUserId;

    @CreationTimestamp
    private LocalDateTime dateDelivery;
}

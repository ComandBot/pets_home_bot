package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "owners")
public class Owner {

    @EmbeddedId
    OwnerId ownerId;

    @CreationTimestamp
    private LocalDateTime dateDelivery;

    @OneToMany(mappedBy = "owner")
    private List<Report> reports;
}
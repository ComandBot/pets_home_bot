package ru.skypro.pets_home_bot.api_bot.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private byte[] photo;

    @Column(columnDefinition = "TEXT")
    private String diet;

    @Column(columnDefinition = "TEXT")
    private String condition;

    @Column(columnDefinition = "TEXT")
    private String behaviour;

    @Column(name = "is_viewed")
    private boolean isViewed;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "pet_user_id",
                    referencedColumnName = "pet_user_id"),
            @JoinColumn(
                    name = "pet_id",
                    referencedColumnName = "pet_id")
    })
    private Owner owner;
}

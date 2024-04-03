package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.OwnerId;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, OwnerId> {
    Optional<Owner> findByOwnerId(OwnerId ownerId);
}

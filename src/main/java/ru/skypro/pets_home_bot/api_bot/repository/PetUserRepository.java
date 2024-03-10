package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;

public interface PetUserRepository extends JpaRepository<PetUser, Integer> {
}

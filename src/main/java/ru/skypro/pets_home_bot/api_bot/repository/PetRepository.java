package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.pets_home_bot.api_bot.model.Pet;

import java.util.Collection;

public interface PetRepository extends JpaRepository<Pet, Integer> {
Collection<Pet> findByName(String name);
}

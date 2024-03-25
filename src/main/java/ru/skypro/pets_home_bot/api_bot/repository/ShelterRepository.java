package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.pets_home_bot.api_bot.enums.PetsTypes;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;

import java.util.List;

public interface ShelterRepository extends JpaRepository<Shelter, Integer> {
    Shelter findByLink(String link);
    List<Shelter> findSheltersByPetsTypes(PetsTypes petsTypes);
}

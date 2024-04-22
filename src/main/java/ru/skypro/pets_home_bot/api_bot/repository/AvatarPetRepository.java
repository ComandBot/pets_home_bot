package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.pets_home_bot.api_bot.model.AvatarPet;
import ru.skypro.pets_home_bot.api_bot.model.Pet;

public interface AvatarPetRepository extends JpaRepository<AvatarPet, Integer> {
    AvatarPet findByPetId(int petId);

    void deleteByPet(Pet pet);
}

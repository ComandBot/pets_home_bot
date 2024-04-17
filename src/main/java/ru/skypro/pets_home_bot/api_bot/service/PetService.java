package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Pet;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PetService {
    /**
     * метод для добавления питомца в базу данных
     * @param pet
     * @return
     */
    Pet addPet(Pet pet);

    /**
     * метод для обновления данных о питомце
     * @param pet
     * @return
     */
    Pet updatePet(Pet pet);

    /**
     * метод для удалении питомца из базы по id
     */
    void deleteById(int petId);

    List<Pet> findPetsByShelterId(int shelterId);

    Pet findByName(String name);

    Optional<Pet> findById(int id);

    List<Pet> findAllByShelterAndIdInOwner(int shelterId);

    List<Pet> findByReportPetsList(int petUserId);
}

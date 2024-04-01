package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Pet;

import java.util.Collection;
import java.util.List;

public interface PetService {
    /**
     * метод для добавления питомца в базу данных
     * @param pet
     * @return
     */
    Pet addPet(Pet pet);

    /**
     * метод для получения информации о питомце по id
     * @param pet
     * @return
     */
    Pet getPet(int pet);

    /**
     * метод для обновления данных о питомце
     * @param pet
     * @return
     */
    Pet updatePet(Pet pet);

    /**
     * метод для удалении питомца из базы по id
     * @param pet
     */
    void deletePet(Integer pet);

    List<Pet> findPetsByShelterId(int shelterId);

    Collection<Pet> findByName(String name);

    Pet findById(int id);
}

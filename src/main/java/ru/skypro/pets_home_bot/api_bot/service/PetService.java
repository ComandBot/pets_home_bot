package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Pet;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PetService {
    /**
     * метод для добавления питомца в базу данных
     * @param pet - добавляемое животное
     * @return - добавленное животное
     */
    Pet addPet(Pet pet);

    /**
     * метод для обновления данных о питомце
     * @param pet - новое животного
     * @return - обновленое животного
     */
    Pet updatePet(Pet pet);

    /**
     * метод для удалении питомца из базы по id
     * @param petId - id животного
     */
    void deleteById(int petId);

    /**
     * Получение списка животных приюта по id
     * @param shelterId - id приюта
     * @return - список животных по id приюта
     */
    List<Pet> findPetsByShelterId(int shelterId);

    /**
     * Поиск животного по его имени
     * @param name - имя животного
     * @return - найденное животного
     */
    Pet findByName(String name);

    /**
     * Получение опционала животного по id
     * @param id - id животного
     * @return - полученное животное
     */
    Optional<Pet> findById(int id);

    /**
     * Получение всех не усыновленных животных в приюте по его id
     * @param shelterId - id приюта
     * @return - список всех животных
     */
    List<Pet> findAllByShelterAndIdInOwner(int shelterId);

    /**
     *Получение животых пользователя по его id на которых нужно отправлять отчеты
     * @param petUserId - id пользователя
     * @return - список животных
     */
    List<Pet> findByReportPetsList(int petUserId);
}

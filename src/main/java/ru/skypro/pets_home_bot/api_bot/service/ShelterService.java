package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import java.util.List;
import java.util.Optional;

public interface ShelterService {

    /**
     * Получение списка приютов для котов
     * @return - список приютов
     */
    List<Shelter> findByCatShelters();

    /**
     * Получение списка приютов для собак
     * @return - список приютов
     */
    List<Shelter> findByDogShelters();

    /**
     * Получение приюта по его id
     * @param id - id приюта
     * @return - опционал приюта
     */
    Optional<Shelter> findByShelterId(int id);

    /**
     * Добавление приюта в базу
     * @param shelter - добавляемый приют
     * @return - добавленный приют
     */
    Shelter add(Shelter shelter);

    /**
     * Получение списка всех приютов
     * @return - список всех приютов
     */
    List<Shelter> findAll();

    /**
     * Удаление приюта по id
     * @param id - id приюта
     */
    void deleteById(int id);
}

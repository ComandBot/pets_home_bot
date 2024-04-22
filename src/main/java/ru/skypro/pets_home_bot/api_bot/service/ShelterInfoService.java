package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.enums.PetsTypes;
import ru.skypro.pets_home_bot.api_bot.model.ShelterInfo;
import java.util.List;
import java.util.Optional;

/**
 * Сервис по работе с информацией об отчетах
 */
public interface ShelterInfoService {

    /**
     * Получение списка информаций по id приюта
     * @param id - id приюта
     * @return - список информаций приютов
     */
    List<ShelterInfo> findAllByShelterId(int id);

    /**
     * Получение опционала информации по id
     * @param id - id опционала
     * @return - опционал информации
     */
    Optional<ShelterInfo> findById(int id);

    /**
     * Добавление информации в базу данных
     * @param shelterInfo - добавляемая информация
     * @return - добавленная информация
     */
    ShelterInfo save(ShelterInfo shelterInfo);

    /**
     * Получение списка животных определенного типа
     * @param petsTypes - тип животного(кошка или собака)
     * @return - список всех животных одного типа
     */
    List<ShelterInfo> findAllByShelterPetsTypes(PetsTypes petsTypes);

    /**
     * Получение списка всех информаций
     * @return - список информаций
     */
    List<ShelterInfo> findAll();

    /**
     * Удаление информации по ее id
     * @param id - id информации
     */
    void deleteById(int id);
}

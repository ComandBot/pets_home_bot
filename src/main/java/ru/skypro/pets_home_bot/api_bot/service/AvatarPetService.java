package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.AvatarPet;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import java.util.List;
import java.util.Optional;

/**
 * Сервис по работе с аватарами животных
 */
public interface AvatarPetService {

    /**
     * Сохраняет аватара животного в базу данных
     * @param petId - id животного
     * @param petDescription - описание животного
     * @param avatar - фото животного
     * @return - возвращает объект типа AvatarPet
     */
    AvatarPet save(int petId, String petDescription, byte[] avatar);

    /**
     * Получаеи опционал аватара по id животного
     * @param petId - id животного
     * @return - опционал
     */
    Optional<AvatarPet> findAvatarPetByPetId(int petId);

    /**
     * Удаление аватара животного из базы
     * @param pet - животное, аватар которого хотим удалить
     */
    void deleteByPet(Pet pet);

    /**
     * Добавлеие животного в базу
     * @param avatarPet - добавляемый аватар
     * @return - добавленный аватар
     */
    AvatarPet add(AvatarPet avatarPet);

    /**
     * Возвращает список всех аватаров
     * @return - список аватаров
     */
    List<AvatarPet> getAvatarsPets();
}

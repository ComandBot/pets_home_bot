package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.ShelterScheme;

/**
 * Сервис по работе со схемами подъезда
 */
public interface ShelterSchemeService {

    /**
     * Добавление схемы подъезда в базу
     * @param shelterId - id приюта
     * @param scheme - фото схемы
     * @return - добавленная схема
     */
    ShelterScheme add(int shelterId, byte[] scheme);

    /**
     * Получение схемы подъезда по id приюта
     * @param shelterId - id приюта
     * @return - схема подъезда
     */
    ShelterScheme findByShelterId(int shelterId);
}

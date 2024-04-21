package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.PetUser;

import java.util.Optional;

public interface PetUserService {
    /**
     * Регистрация нового усыновителя потенциального усыновителя
     * @param petUser - ссылка на нового усыновителя
     * @return - возвращает ссылку на нового усыновителя
     */
    PetUser add(PetUser petUser);

    /**
     * Убирает регистрацию усыновителя
     * @param idPetUser - id усыновителя
     */
    void deletePetUser(int idPetUser);

    /**
     * Поиск усыновителя(или владельца животного, если усыновитель является владельцем на испытательном сроке)
     * по chatId, для связи с волонтером.
     * @param chatIdPetUser - chatId усыновителя
     * @return - возвращается
     */
    PetUser findByChatIdPetUser(long chatIdPetUser);

    /**
     * Получение опционала пользователя по его id
     * @param id - id усыновителя
     * @return - опционал пользователя
     */
    Optional<PetUser> findById(int id);
}

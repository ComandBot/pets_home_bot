package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.PetUser;

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
     * @return - возвращает удаленног усыновителя или null
     */
    PetUser deletePetUser(int idPetUser);

    /**
     * Поиск усыновителя(или владельца животного, если усыновитель является владельцем на испытательном сроке)
     * по chatId, для связи с волонтером.
     * @param chatIdPetUser - chatId усыновителя
     * @return - возвращается
     */
    PetUser findByChatIdPetUser(int chatIdPetUser);
}

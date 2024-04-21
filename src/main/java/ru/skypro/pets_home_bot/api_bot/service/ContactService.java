package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Contact;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import java.util.List;

/**
 * Сервис по работе с контактной информацией от пользователей
 */
public interface ContactService {

    /**
     * Добавляет телефонный номер усыновителя
     * @param contact - добавляемый телефонный номер
     * @return - добавленный телефонный номер
     */
    Contact add(Contact contact);

    /**
     * Получение телефонного номера по усыновителю
     * @param petUser - усыновитель
     * @return - телефонный номер
     */
    Contact findByPetUser(PetUser petUser);

    /**
     * Получение списка номеров телефонов усыновителя
     * @param petUser - усыновитель
     * @return - список номеров
     */
    List<Contact> findAllByPetUser(PetUser petUser);
}

package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;

public interface VolunteerService {

    /**
     * Регистрация волонтера
     * @param volunteer - ссылка на нового волнтера
     * @return - возвращает ссылку на новго волонтера
     */
    Volunteer add(Volunteer volunteer);

    /**
     * Удаление волонтера из системы приютов
     * @param volunteerId id волонтера
     */
    void delete(int volunteerId);

    /**
     * При вызове волонтера усыновителем устанавливает связь с ним
     * @param petUser - усыновитель вызывающий волонтера
     * @param volunteerId - id волонтера с которым устанавливаем связь
     * @return - возвращает отредактированного волонтера с полем workPetUserId не равным null,
     * либо возвращает null если волонтер не найден
     */
    Volunteer setWorkPetUser(int volunteerId, PetUser petUser);

    /**
     * Если волонтер отработал с усыновителем, то высвобождаем волонтера
     * @param volunteerId - id волонтера которого освобождаем
     * @return - возвращает свободного волонтера у которого поле workPetUserId равно null
     * либо возвращает null если волонтер не найден
     */
    Volunteer removeWorkPetUser(int volunteerId);

    Volunteer findByChatIdVolunteer(long chatId);
}

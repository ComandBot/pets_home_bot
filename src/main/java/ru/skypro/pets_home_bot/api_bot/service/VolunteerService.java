package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import java.util.List;

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
     * Получение волонтера по chatId
     * @param chatId - id чата волонтера
     * @return - волонтер
     */
    Volunteer findByChatIdVolunteer(long chatId);

    /**
     * Нахождение не задействованного волонтера
     * @return - волонтер
     */
    Volunteer findFirstByWorkUserIdIsNull();

    /**
     * Получение волонтера общающегося с пользователем
     * @param petUser - пользователь
     * @return - волонтер
     */
    Volunteer findByWorkUserId(PetUser petUser);

    /**
     * Получение всех волонтеров
     * @return - список всех волонтеров
     */
    List<Volunteer> getAll();
}

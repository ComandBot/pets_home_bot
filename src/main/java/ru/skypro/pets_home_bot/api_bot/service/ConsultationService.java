package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Consultation;

import java.util.List;
import java.util.Optional;

/**
 * Сервис по работе с дополнительной информацией по усыновлению животных
 */
public interface ConsultationService {

    /**
     * Возвращает список информации по id приюта
     * @param shelterId - id приюта
     * @return - список информации
     */
    List<Consultation> findConsultationsByShelterId(int shelterId);

    /**
     * Поиск консультации по id
     * @param consultationId - id консультации
     * @return - опционал консультации
     */
    Optional<Consultation> findConsultationById(int consultationId);

    /**
     * Длбавление информации в базу данных
     * @param consultation - добавляемая информация
     * @return - добавленная информация
     */
    Consultation add(Consultation consultation);

    /**
     * Получение списка информаций со всех приютов
     * @return - список информаций
     */
    List<Consultation> findAll();

    /**
     * Удаление информации по id
     * @param id - id информации
     */
    void deleteById(int id);
}

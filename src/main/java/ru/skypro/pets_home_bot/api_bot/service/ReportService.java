package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.projections.ReportIdDate;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с отчетами
 */
public interface ReportService {

    /**
     * Добавление отчета в базу данных
     * @param report - добавляемый отчет
     * @return - добавленный отчет
     */
    Report add(Report report);

    /**
     * Получение списка всех не просмотренных отчетов
     * @return - список не просмотреных отчетов
     */
    List<Report> findAllByIsViewedFalse();

    /**
     * Получение информации об отчетах, которые не просмотренны
     * @return - информация о не просмотренных отчетов
     */
    List<ReportIdDate> findAllByIsViewedFalseProjection();

    /**
     * Получение опционала отчета по его id
     * @param id - id отчета
     * @return - опционал отчета
     */
    Optional<Report> findById(int id);

    /**
     * Получение информации по об отчетах по владельцу
     * @param petUserId - id владельца
     * @param petId - id животного
     * @return - список информации об отчетах
     */
    List<ReportIdDate> findAllByPetUserIdAndPetId(int petUserId, int petId);

    /**
     * Удалить все отчеты пользователя
     * @param owner - пользователь отчеты которого нужно удалить
     */
    void deleteAllByOwner(Owner owner);
}

package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.OwnerId;
import java.util.List;
import java.util.Optional;

/**
 * Сервис по работе с владельцами животных
 */
public interface OwnerService {

    /**
     * Добавление владельца питомца на испытательный срок. Испытательный срок определяется от текущей даты усыновления.
     * @param owner - в качестве параметра принимает ссылку на владельца
     * @return - возвращает ссылку на добавленного владельца
     */
    Owner addOwner(Owner owner);

    /**
     * Поиск владельца по его id
     * @param ownerId - id владельца
     * @return - опционал найденного владельца
     */
    Optional<Owner> findByOwnerId(OwnerId ownerId);

    /**
     * Поиск владельца по id усыновителя и id животного
     * @param petUserId - id усыновителя
     * @param petId - id животного
     * @return - опционал найденного владельца
     */
    Optional<Owner> findByPetUserIdAndPetId(int petUserId, int petId);

    /**
     * Поиск всех владельцев у которых дата усыновления {@code null}, т. е. заявки
     * @return - список всех владельцев (заявок)
     */
    List<Owner> findAllByDateDeliveryIsNull();

    /**
     * Получение владельца по id усыновителя и id животного, где дата усыновления {@code null}
     * @param petUserId - id усыновителя
     * @param petId - id животного
     * @return - опционал владельца
     */
    Optional<Owner> findByPetIdAndPetUserIdWhereDateNull(int petUserId, int petId);

    /**
     * Сохранение владельца в базу данных
     * @param owner - сохраняемый владелец
     */
    void save(Owner owner);

    /**
     * Удаление владельца из базы данных по его id
     * @param ownerId - id владельца
     */
    void deleteByOwnerId(OwnerId ownerId);

    /**
     * Получение списка владельцев нходящихся на испытательном сроке
     * @return - список владельцев
     */
    List<Owner> findAllByDateDeliveryBetweenBeginAndEndTestPeriod();

    /**
     * Получение списков владельцев, чей испытательный срок завершен
     * @return - список владельцев
     */
    List<Owner> findAllByDateDeliveryMoreEndTestPeriod();

    /**
     * Получение списка владельцев просрочивших отправку отчета на два дня
     * @return - список владельцев
     */
    List<Owner> getOwnersAfterTwoDaysReport();

}

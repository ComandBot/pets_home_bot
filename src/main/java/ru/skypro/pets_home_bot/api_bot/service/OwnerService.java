package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.OwnerId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OwnerService {
    /**
     * Добавление владельца питомца на испытательный срок. Испытательный срок определяется от текущей даты усыновления.
     * @param owner - в качестве параметра принимает ссылку на владельца
     * @return - возвращает ссылку на добавленного владельца
     */
    Owner addOwner(Owner owner);

    /**
     * Метод отвечающий за увеличение испытательного срока владельца
     * @param ownerId - id владельца представляющий из себя составной ключ из id животного и id усыновителя
     * @param days - количество дополнительных дней к испытательному сроку
     * @return - возвращает отредактированного владельца или null, если таковой не был найден
     */
    Owner extensionOfTestPeriod(OwnerId ownerId, int days);

    /**
     * Удаление владелца из базы в случае пройденного им испытательного срока.
     * Удаляем владельца из таблицы owners, усыновленное животное из таблицы pets, а также все лтчеты,
     * т.к. владелец прошел
     * испытательный срок и является полноценным хозяевом, а следовательно учитывать животное в приюте и отчеты
     * не имеетсмысла
     * @param ownerId - id владельца представляющий из себя составной ключ из id животного и id усыновителя
     * @return возвращает удаленного владельца или null, если владелец не был найден
     */
    Owner deleteOwnerIfTestPeriodPassed(OwnerId ownerId);


    /**
     * Изымаем животное у владельца, удаляя его, а также нужно удалить все отчеты связанные с владельцем.
     * Животное остается зарегестрированным в приюте и его могут
     * усыновить другие усыновители.
     * @param ownerId - id владельца представляющий из себя составной ключ из id животного и id усыновителя
     * @return возвращает удаленного владельца или null, если владелец не был найден
     */
    Owner deleteOwnerIfTestPeriodNotPassed(OwnerId ownerId);

    Optional<Owner> findByOwnerId(OwnerId ownerId);

    Optional<Owner> findByPetUserIdAndPetId(int petUserId, int petId);

    List<Owner> findAllByDateDeliveryIsNull();

    Optional<Owner> findByPetIdAndPetUserIdWhereDateNull(int petUserId, int petId);
    void save(Owner owner);

    void deleteByOwnerId(OwnerId ownerId);

    List<Owner> findAllByDateDeliveryBetweenBeginAndEndTestPeriod();

}

package ru.skypro.pets_home_bot.api_bot.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.OwnerId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, OwnerId> {
    Optional<Owner> findByOwnerId(OwnerId ownerId);

    @Query(value = "select * from owners\n" +
            "where pet_user_id = ?1 and pet_id = ?2 and date_delivery notnull ",
            nativeQuery = true)
    Optional<Owner> findByPetUserIdAndPetId(int petUserId, int petId);

    @Query(value = "select * from owners\n" +
            "where pet_user_id = ?1 and pet_id = ?2 and date_delivery is null",
            nativeQuery = true)
    Optional<Owner> findByPetIdAndPetUserIdWhereDateNull(int petUserId, int petId);

    List<Owner> findAllByDateDeliveryIsNull();
    @Query(value = "select * from owners\n" +
            "where now() between date_delivery and date_delivery + test_period * interval '1 day'",
            nativeQuery = true)
    List<Owner> findAllByDateDeliveryBetweenBeginAndEndTestPeriod();

    @Query(value = "select * from owners\n" +
            "where  now() > date_delivery + test_period * interval '1 day'",
            nativeQuery = true)
    List<Owner> findAllByDateDeliveryMoreEndTestPeriod();
    void deleteByOwnerId(OwnerId ownerId);

    @Query(value = "select * from owners\n" +
            "where (pet_user_id, pet_id) in (select pet_user_id, pet_id from reports\n" +
            "                                GROUP BY pet_user_id, pet_id\n" +
            "                                HAVING now() > max(date_report) + interval '2 day')",
            nativeQuery = true)
    List<Owner> getOwnersAfterTwoDaysReport();
}

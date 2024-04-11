package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.OwnerId;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, OwnerId> {
    Optional<Owner> findByOwnerId(OwnerId ownerId);

    @Query(value = "select * from owners\n" +
            "where pet_user_id = ?1 and pet_id = ?2 and date_delivery notnull ",
            nativeQuery = true)
    Optional<Owner> findByPetIdAndPetUserId(int petUserId, int petId);

    List<Owner> findAllByDateDeliveryIsNull();
}

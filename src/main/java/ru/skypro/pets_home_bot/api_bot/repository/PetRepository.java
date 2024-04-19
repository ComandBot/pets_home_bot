package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.pets_home_bot.api_bot.model.Pet;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findAllByShelterId(int shelterId);
    @Query(value = "select * from pets\n" +
            "where shelter_id = ?1 and id not in (select pet_id from owners)",
            nativeQuery = true)
    List<Pet> findAllByShelterAndIdInOwner(int shelterId);

    @Query(value = "select * from pets\n" +
            "where id in (select pet_id from owners\n" +
            "                           where pet_user_id = ?1 and date_delivery is not null)",
    nativeQuery = true)
    List<Pet> findByReportPetsList(int petUserId);
    Pet findByName(String name);

    void deleteById(int petId);
}

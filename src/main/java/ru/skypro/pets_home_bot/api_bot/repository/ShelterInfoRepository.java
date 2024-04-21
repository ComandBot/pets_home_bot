package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.pets_home_bot.api_bot.enums.PetsTypes;
import ru.skypro.pets_home_bot.api_bot.model.ShelterInfo;

import java.util.List;

public interface ShelterInfoRepository extends JpaRepository<ShelterInfo, Integer> {
    List<ShelterInfo> findAllByShelterId(int id);

    List<ShelterInfo> findAllByShelter_PetsTypes(PetsTypes petsTypes);
}

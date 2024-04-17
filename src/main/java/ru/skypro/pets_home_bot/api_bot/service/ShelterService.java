package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Shelter;

import java.util.List;
import java.util.Optional;

public interface ShelterService {
    List<Shelter> findByCatShelters();

    List<Shelter> findByDogShelters();

    Optional<Shelter> findByShelterId(int id);

    Shelter add(Shelter shelter);

    List<Shelter> findAll();

    void deleteById(int id);
}

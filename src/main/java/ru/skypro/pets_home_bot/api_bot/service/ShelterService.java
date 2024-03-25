package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Shelter;

import java.util.List;

public interface ShelterService {
    Shelter findByShelterLink(String link);

    List<Shelter> findByCatShelters();

    List<Shelter> findByDogShelters();
}

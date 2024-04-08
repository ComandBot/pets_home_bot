package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.ShelterScheme;

public interface ShelterSchemeService {
    ShelterScheme add(int shelterId, byte[] scheme);

    ShelterScheme findByShelterId(int shelterId);
}

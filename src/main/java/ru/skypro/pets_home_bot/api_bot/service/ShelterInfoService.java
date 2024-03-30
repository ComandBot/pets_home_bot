package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.ShelterInfo;

import java.util.List;
import java.util.Optional;

public interface ShelterInfoService {
    List<ShelterInfo> findAllByShelterId(int id);

    Optional<ShelterInfo> findById(int id);
}
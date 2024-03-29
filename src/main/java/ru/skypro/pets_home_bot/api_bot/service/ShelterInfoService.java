package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.ShelterInfo;

import java.util.List;

public interface ShelterInfoService {
    List<ShelterInfo> findAllByShelterId(int id);
}

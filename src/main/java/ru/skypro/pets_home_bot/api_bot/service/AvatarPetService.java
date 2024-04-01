package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.AvatarPet;

public interface AvatarPetService {
    AvatarPet save(int petId, String petDescription, byte[] avatar);
}

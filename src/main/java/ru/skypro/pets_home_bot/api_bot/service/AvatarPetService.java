package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.AvatarPet;

import java.util.Optional;

public interface AvatarPetService {
    AvatarPet save(int petId, String petDescription, byte[] avatar);
    Optional<AvatarPet> findAvatarPetByPetId(int petId);
}
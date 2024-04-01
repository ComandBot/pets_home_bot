package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.pets_home_bot.api_bot.model.AvatarPet;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.repository.AvatarPetRepository;
import ru.skypro.pets_home_bot.api_bot.repository.PetRepository;
import ru.skypro.pets_home_bot.api_bot.repository.PetUserRepository;
import ru.skypro.pets_home_bot.api_bot.service.AvatarPetService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class AvatarPetServiceImpl implements AvatarPetService {
    private final AvatarPetRepository avatarPetRepository;
    private final PetRepository petRepository;
    public AvatarPetServiceImpl(AvatarPetRepository avatarPetRepository, PetUserRepository petUserRepository, PetRepository petRepository) {
        this.avatarPetRepository = avatarPetRepository;
        this.petRepository = petRepository;
    }

    @Override
    public AvatarPet save(int petId, String petDescription, byte[] avatar) {
        Optional<Pet> petOptional = petRepository.findById(petId);
        if (petOptional.isEmpty()) {
            throw new NoSuchElementException("Животное по id не найдено");
        }
        Pet pet = petOptional.get();
        AvatarPet avatarPet = new AvatarPet();
        avatarPet.setDescription(petDescription);
        avatarPet.setPet(pet);
        avatarPet.setData(avatar);
        return avatarPetRepository.save(avatarPet);
    }
}

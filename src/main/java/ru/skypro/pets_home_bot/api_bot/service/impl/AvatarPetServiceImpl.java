package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.pets_home_bot.api_bot.model.AvatarPet;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.repository.AvatarPetRepository;
import ru.skypro.pets_home_bot.api_bot.repository.PetRepository;
import ru.skypro.pets_home_bot.api_bot.service.AvatarPetService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AvatarPetServiceImpl implements AvatarPetService {
    private final AvatarPetRepository avatarPetRepository;
    private final PetRepository petRepository;
    public AvatarPetServiceImpl(AvatarPetRepository avatarPetRepository, PetRepository petRepository) {
        this.avatarPetRepository = avatarPetRepository;
        this.petRepository = petRepository;
    }

    @Override
    @Transactional
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

    @Override
    @Transactional
    public Optional<AvatarPet> findAvatarPetByPetId(int petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()) {
            return Optional.empty();
        }
        AvatarPet avatarPet = avatarPetRepository.findByPetId(petId);
        if (avatarPet == null) {
            return Optional.empty();
        }
        return Optional.of(avatarPet);
    }

    @Override
    @Transactional
    public void deleteByPet(Pet pet) {
        avatarPetRepository.deleteByPet(pet);
    }

    @Override
    public AvatarPet add(AvatarPet avatarPet) {
        return avatarPetRepository.save(avatarPet);
    }

    @Override
    public List<AvatarPet> getAvatarsPets() {
        return avatarPetRepository.findAll();
    }
}

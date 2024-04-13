package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.repository.PetRepository;
import ru.skypro.pets_home_bot.api_bot.service.PetService;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {
private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    @Override
    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }
    @Override
    public Pet updatePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void deleteById(int petId) {
        petRepository.deleteById(petId);
    }

    @Override
    public List<Pet> findPetsByShelterId(int shelterId) {
        return petRepository.findAllByShelterId(shelterId);
    }

    @Override
    public Collection<Pet> findByName(String name) {
        return petRepository.findByName(name);
    }

    @Override
    public Optional<Pet> findById(int id) {
        return petRepository.findById(id);
    }

    @Override
    public List<Pet> findAllByShelterAndIdInOwner(int shelterId) {
        return petRepository.findAllByShelterAndIdInOwner(shelterId);
    }

    @Override
    public List<Pet> findByReportPetsList(int petUserId) {
        return petRepository.findByReportPetsList(petUserId);
    }
}

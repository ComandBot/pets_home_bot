package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.enums.PetsTypes;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.repository.ShelterRepository;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterServiceImpl implements ShelterService {
    private final ShelterRepository shelterRepository;

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @Override
    public Shelter findByShelterLink(String link) {
        return shelterRepository.findByLink(link);
    }

    @Override
    public List<Shelter> findByCatShelters() {
        return shelterRepository.findSheltersByPetsTypes(PetsTypes.CAT);
    }

    @Override
    public List<Shelter> findByDogShelters() {
        return shelterRepository.findSheltersByPetsTypes(PetsTypes.DOG);
    }

    @Override
    public Optional<Shelter> findByShelterId(int id) {
        return shelterRepository.findById(id);
    }
}

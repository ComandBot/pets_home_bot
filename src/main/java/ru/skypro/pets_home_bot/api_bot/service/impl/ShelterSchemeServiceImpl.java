package ru.skypro.pets_home_bot.api_bot.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.model.ShelterScheme;
import ru.skypro.pets_home_bot.api_bot.repository.ShelterSchemeRepository;
import ru.skypro.pets_home_bot.api_bot.service.ShelterSchemeService;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;

import java.util.Optional;
@Service
@Transactional
public class ShelterSchemeServiceImpl implements ShelterSchemeService {

    private final ShelterService shelterService;
    private final ShelterSchemeRepository shelterSchemeRepository;

    public ShelterSchemeServiceImpl(ShelterService shelterService,
                                    ShelterSchemeRepository shelterSchemeRepository) {
        this.shelterService = shelterService;
        this.shelterSchemeRepository = shelterSchemeRepository;
    }

    @Override
    public ShelterScheme add(int shelterId, byte[] scheme) {
        Optional<Shelter> optionalShelter = shelterService.findByShelterId(shelterId);
        if (optionalShelter.isEmpty()) {
            return null;
        }
        ShelterScheme shelterScheme = new ShelterScheme();
        shelterScheme.setShelter(optionalShelter.get());
        shelterScheme.setScheme(scheme);
        return shelterSchemeRepository.save(shelterScheme);
    }

    @Override
    public ShelterScheme findByShelterId(int shelterId) {
        return shelterSchemeRepository.findByShelterId(shelterId);
    }
}

package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.enums.PetsTypes;
import ru.skypro.pets_home_bot.api_bot.model.ShelterInfo;
import ru.skypro.pets_home_bot.api_bot.repository.ShelterInfoRepository;
import ru.skypro.pets_home_bot.api_bot.service.ShelterInfoService;
import java.util.List;
import java.util.Optional;

@Service
public class ShelterInfoServiceImpl implements ShelterInfoService {

    private final ShelterInfoRepository shelterInfoRepository;

    public ShelterInfoServiceImpl(ShelterInfoRepository shelterInfoRepository) {
        this.shelterInfoRepository = shelterInfoRepository;
    }

    @Override
    public List<ShelterInfo> findAllByShelterId(int id) {
        return shelterInfoRepository.findAllByShelterId(id);
    }

    @Override
    public Optional<ShelterInfo> findById(int id) {
        return shelterInfoRepository.findById(id);
    }

    @Override
    public ShelterInfo save(ShelterInfo shelterInfo) {
        return shelterInfoRepository.save(shelterInfo);
    }

    @Override
    public List<ShelterInfo> findAllByShelterPetsTypes(PetsTypes petsTypes) {
        return shelterInfoRepository.findAllByShelter_PetsTypes(petsTypes);
    }

    @Override
    public List<ShelterInfo> findAll() {
        return shelterInfoRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        shelterInfoRepository.deleteById(id);
    }
}

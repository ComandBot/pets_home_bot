package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.model.ShelterInfo;
import ru.skypro.pets_home_bot.api_bot.repository.ShelterInfoRepository;
import ru.skypro.pets_home_bot.api_bot.service.ShelterInfoService;
import java.util.List;
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
}

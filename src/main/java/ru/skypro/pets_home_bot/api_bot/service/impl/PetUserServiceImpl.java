package ru.skypro.pets_home_bot.api_bot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.repository.PetUserRepository;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;

@Service
@Slf4j
public class PetUserServiceImpl implements PetUserService {

    private final PetUserRepository petUserRepository;

    public PetUserServiceImpl(PetUserRepository petUserRepository) {
        this.petUserRepository = petUserRepository;
    }


    @Override
    public PetUser add(PetUser petUser) {
        return petUserRepository.save(petUser);
    }

    @Override
    public PetUser deletePetUser(int idPetUser) {
        PetUser petUser = petUserRepository.findById(idPetUser).orElseThrow();
        petUserRepository.delete(petUser);
        return petUser;
    }

    @Override
    public PetUser findByChatIdPetUser(long chatIdPetUser) {
        return petUserRepository.findByChatId(chatIdPetUser);
    }
}

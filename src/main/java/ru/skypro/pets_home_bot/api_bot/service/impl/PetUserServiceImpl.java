package ru.skypro.pets_home_bot.api_bot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.repository.PetUserRepository;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;

import java.util.Optional;

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
    public void deletePetUser(int idPetUser) {
        PetUser petUser = petUserRepository.findById(idPetUser).orElseThrow();
        petUserRepository.delete(petUser);
    }

    @Override
    public PetUser findByChatIdPetUser(long chatIdPetUser) {
        return petUserRepository.findByChatId(chatIdPetUser);
    }

    @Override
    public Optional<PetUser> findById(int id) {
        return petUserRepository.findById(id);
    }
}

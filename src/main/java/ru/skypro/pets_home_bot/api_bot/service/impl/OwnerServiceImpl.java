package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.OwnerId;
import ru.skypro.pets_home_bot.api_bot.repository.OwnerRepository;
import ru.skypro.pets_home_bot.api_bot.service.OwnerService;

import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner extensionOfTestPeriod(OwnerId ownerId, int days) {
        return null;
    }

    @Override
    public Owner deleteOwnerIfTestPeriodPassed(OwnerId ownerId) {
        return null;
    }

    @Override
    public Owner deleteOwnerIfTestPeriodNotPassed(OwnerId ownerId) {
        return null;
    }

    @Override
    public Optional<Owner> findByOwnerId(OwnerId ownerId) {
        return ownerRepository.findByOwnerId(ownerId);
    }

    @Override
    public Optional<Owner> findByPetIdAndPetUserId(int petId, int petUserId) {
        return ownerRepository.findByPetIdAndPetUserId(petId, petUserId);
    }
}

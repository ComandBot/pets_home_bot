package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.OwnerId;
import ru.skypro.pets_home_bot.api_bot.repository.OwnerRepository;
import ru.skypro.pets_home_bot.api_bot.service.OwnerService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    public Optional<Owner> findByOwnerId(OwnerId ownerId) {
        return ownerRepository.findByOwnerId(ownerId);
    }

    @Override
    public Optional<Owner> findByPetUserIdAndPetId(int petUserId, int petId) {
        return ownerRepository.findByPetUserIdAndPetId(petUserId, petId);
    }

    @Override
    public List<Owner> findAllByDateDeliveryIsNull() {
        return ownerRepository.findAllByDateDeliveryIsNull();
    }

    @Override
    public Optional<Owner> findByPetIdAndPetUserIdWhereDateNull(int petUserId, int petId) {
        return ownerRepository.findByPetIdAndPetUserIdWhereDateNull(petUserId, petId);
    }

    @Override
    public void save(Owner owner) {
        ownerRepository.save(owner);
    }
    @Transactional
    @Override
    public void deleteByOwnerId(OwnerId ownerId) {
        ownerRepository.deleteByOwnerId(ownerId);
    }

    @Override
    public List<Owner> findAllByDateDeliveryBetweenBeginAndEndTestPeriod() {
        return ownerRepository.findAllByDateDeliveryBetweenBeginAndEndTestPeriod();
    }

    @Override
    public List<Owner> findAllByDateDeliveryMoreEndTestPeriod() {
        return ownerRepository.findAllByDateDeliveryMoreEndTestPeriod();
    }

    @Override
    public List<Owner> getOwnersAfterTwoDaysReport() {
        return ownerRepository.getOwnersAfterTwoDaysReport();
    }
}

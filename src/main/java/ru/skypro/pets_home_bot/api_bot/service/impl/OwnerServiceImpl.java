package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.OwnerId;
import ru.skypro.pets_home_bot.api_bot.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Override
    public Owner addOwner(Owner owner) {
        return null;
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
}

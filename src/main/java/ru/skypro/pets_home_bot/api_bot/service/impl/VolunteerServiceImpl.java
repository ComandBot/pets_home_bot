package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Override
    public Volunteer add(Volunteer volunteer) {
        return null;
    }

    @Override
    public Volunteer delete(int volunteerId) {
        return null;
    }

    @Override
    public Volunteer setWorkPetUser(int volunteerId, PetUser petUser) {
        return null;
    }

    @Override
    public Volunteer removeWorkPetUser(int volunteerId) {
        return null;
    }
}

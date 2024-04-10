package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.repository.VolunteerRepository;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public Volunteer add(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public void delete(int volunteerId) {
        volunteerRepository.deleteById(volunteerId);
    }

    @Override
    public Volunteer setWorkPetUser(int volunteerId, PetUser petUser) {
        return null;
    }

    @Override
    public Volunteer removeWorkPetUser(int volunteerId) {
        return null;
    }

    @Override
    public Volunteer findByChatIdVolunteer(long chatId) {
        return volunteerRepository.findByChatId(chatId);
    }

    @Override
    public Volunteer findFirstByWorkUserIdIsNull() {
        return volunteerRepository.findFirstByWorkUserIdIsNull();
    }

    @Override
    public Volunteer findByWorkUserId(PetUser petUser) {
        return volunteerRepository.findByWorkUserId(petUser);
    }
}

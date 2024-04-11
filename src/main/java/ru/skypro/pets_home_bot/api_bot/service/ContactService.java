package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Contact;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;

public interface ContactService {
    Contact add(Contact contact);
    Contact findByPetUser(PetUser petUser);
}

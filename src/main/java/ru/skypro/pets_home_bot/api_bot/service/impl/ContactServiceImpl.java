package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.model.Contact;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.repository.ContactRepository;
import ru.skypro.pets_home_bot.api_bot.service.ContactService;
@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact add(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact findByPetUser(PetUser petUser) {
        return contactRepository.findByPetUser(petUser);
    }
}

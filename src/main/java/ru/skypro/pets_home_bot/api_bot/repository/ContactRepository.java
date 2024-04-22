package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.pets_home_bot.api_bot.model.Contact;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Contact findByPetUser(PetUser petUser);

    List<Contact> findAllByPetUser(PetUser petUser);
}

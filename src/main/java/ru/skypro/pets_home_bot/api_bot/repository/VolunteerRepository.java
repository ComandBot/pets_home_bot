package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
}

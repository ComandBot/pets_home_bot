package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}

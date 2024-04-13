package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    @Query(value = "select * from reports where NOT is_viewed"
    , nativeQuery = true)
    List<Report> findAllByIsViewedFalse();

    @Query(value = "select id, date_report from reports where NOT is_viewed"
            , nativeQuery = true)
    List<Object[]> findByActualReport();

    void deleteAllByOwner(Owner owner);
}

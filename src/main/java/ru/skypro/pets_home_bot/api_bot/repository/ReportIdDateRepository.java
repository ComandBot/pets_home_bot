package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.projections.ReportIdDate;

import java.util.List;

public interface ReportIdDateRepository extends JpaRepository<Report, Integer> {
    @Query(value = "select r.id, r.date_report from reports r where NOT r.is_viewed"
            , nativeQuery = true)
    List<ReportIdDate> findAllByViewedIsFalse();
}

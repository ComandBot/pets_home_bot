package ru.skypro.pets_home_bot.api_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.projections.ReportIdDate;

import java.util.List;

public interface ReportIdDateRepository extends JpaRepository<Report, Integer> {
    @Query(value = "select r.id as id, r.date_report as dateReport from reports r where NOT r.is_viewed"
            , nativeQuery = true)
    List<ReportIdDate> findAllByViewedIsFalse();

    @Query(value = "select r.id as id, r.date_report as dateReport from reports r " +
            "where r.pet_user_id = ?1 and r.pet_id = ?2"
            , nativeQuery = true)
    List<ReportIdDate> findAllByPetUserIdAndPetId(int petUserId, int petId);
}

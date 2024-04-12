package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.projections.ReportIdDate;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    Report add(Report report);
    List<Report> findAllByIsViewedFalse();

    List<ReportIdDate> findAllByIsViewedFalseProjection();

    Optional<Report> findById(int id);
}

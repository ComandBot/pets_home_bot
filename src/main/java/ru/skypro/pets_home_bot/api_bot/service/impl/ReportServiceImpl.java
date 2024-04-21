package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.projections.ReportIdDate;
import ru.skypro.pets_home_bot.api_bot.repository.ReportIdDateRepository;
import ru.skypro.pets_home_bot.api_bot.repository.ReportRepository;
import ru.skypro.pets_home_bot.api_bot.service.ReportService;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final ReportIdDateRepository reportIdDateRepository;

    public ReportServiceImpl(ReportRepository reportRepository, ReportIdDateRepository reportIdDateRepository) {
        this.reportRepository = reportRepository;
        this.reportIdDateRepository = reportIdDateRepository;
    }

    @Override
    public Report add(Report report) {
        return reportRepository.save(report);
    }
    @Transactional
    @Override
    public List<Report> findAllByIsViewedFalse() {
        return reportRepository.findAllByIsViewedFalse();
    }

    @Override
    public List<ReportIdDate> findAllByIsViewedFalseProjection() {
        return reportIdDateRepository.findAllByViewedIsFalse();
    }

    @Override
    public Optional<Report> findById(int id) {
        return reportRepository.findById(id);
    }

    @Override
    public List<ReportIdDate> findAllByPetUserIdAndPetId(int petUserId, int petId) {
        return reportIdDateRepository.findAllByPetUserIdAndPetId(petUserId, petId);
    }

    @Transactional
    @Override
    public void deleteAllByOwner(Owner owner) {
        reportRepository.deleteAllByOwner(owner);
    }
}

package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Consultation;

import java.util.List;
import java.util.Optional;

public interface ConsultationService {
    List<Consultation> findConsultationsByShelterId(int shelterId);
    Optional<Consultation> findConsultationById(int consultationId);
    Consultation add(Consultation consultation);
    List<Consultation> findAll();

    void deleteById(int id);
}

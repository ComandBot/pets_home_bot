package ru.skypro.pets_home_bot.api_bot.service;

import ru.skypro.pets_home_bot.api_bot.model.Consultation;

import java.util.List;

public interface ConsultationService {
    List<Consultation> findConsultationsByShelterId(int shelterId);
}

package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.model.Consultation;
import ru.skypro.pets_home_bot.api_bot.repository.ConsultationRepository;
import ru.skypro.pets_home_bot.api_bot.service.ConsultationService;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;

    public ConsultationServiceImpl(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public List<Consultation> findConsultationsByShelterId(int shelterId) {
        return consultationRepository.findAllByShelterId(shelterId);
    }
    @Override
    public Optional<Consultation> findConsultationById(int consultationId) {
        return consultationRepository.findById(consultationId);
    }

    @Override
    public Consultation add(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public List<Consultation> findAll() {
        return consultationRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
         consultationRepository.deleteById(id);
    }
}

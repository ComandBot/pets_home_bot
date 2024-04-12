package ru.skypro.pets_home_bot.api_bot.service.impl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.pets_home_bot.api_bot.model.Consultation;
import ru.skypro.pets_home_bot.api_bot.repository.ConsultationRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.ConsultationServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultationServiceImplTest {

    @InjectMocks
    private ConsultationServiceImpl out;
    @Mock
    private ConsultationRepository consultationRepository;

    @Test
    public void findConsultationsByShelterIdTest() {
        Consultation consultation = new Consultation();
        consultation.setId(1);
        when(consultationRepository.findAllByShelterId(anyInt())).thenReturn(List.of(consultation));
        List<Consultation> consultations = out.findConsultationsByShelterId(1);
        assertNotNull(consultations);
        assertEquals(1, consultations.size());
        assertEquals(1, consultations.get(0).getId());
    }

    @Test
    public void findConsultationByIdTest() {
        Consultation consultation = new Consultation();
        consultation.setId(1);
        when(consultationRepository.findById(anyInt())).thenReturn(Optional.of(consultation));
        Optional<Consultation> result = out.findConsultationById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    public void findConsultationByIdNotFoundTest() {
        when(consultationRepository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<Consultation> result = out.findConsultationById(1);
        assertFalse(result.isPresent());
    }
}
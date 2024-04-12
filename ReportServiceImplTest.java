package ru.skypro.pets_home_bot.api_bot.service.impl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.repository.ReportRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.ReportServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {

    @InjectMocks
    private ReportServiceImpl out;
    @Mock
    private ReportRepository reportRepository;

    @Test
    public void addTest() {
        Report report = new Report();
        report.setId(1);
        when(reportRepository.save(report)).thenReturn(report);
        Report result = out.add(report);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }
}
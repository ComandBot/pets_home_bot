package ru.skypro.pets_home_bot.api_bot.controller;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.pets_home_bot.api_bot.dto.MessageDto;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.repository.PetUserRepository;
import ru.skypro.pets_home_bot.api_bot.repository.ReportIdDateRepository;
import ru.skypro.pets_home_bot.api_bot.repository.ReportRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.PetUserServiceImpl;
import ru.skypro.pets_home_bot.api_bot.service.impl.ReportServiceImpl;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = ReportController.class)
class ReportControllerTest {
    @Autowired
    private MockMvc mockMvc;
    SendResponse sendResponse;
    @MockBean
    private ReportRepository reportRepository;
    @MockBean
    private PetUserRepository petUserRepository;
    @MockBean
    private ReportIdDateRepository reportIdDateRepository;
    @MockBean
    private TelegramBot telegramBot;
    @SpyBean
    private ReportServiceImpl reportService;
    @SpyBean
    private PetUserServiceImpl petUserService;
    @InjectMocks
    private ReportController reportController;


    @BeforeEach
    public void init() {
        sendResponse = Mockito.spy(SendResponse.class);
        ReflectionTestUtils.setField(sendResponse, "ok", true);

    }
    @Test
    void getNotViewedReports() throws Exception {
        Report report1 = new Report();
        report1.setId(1);
        report1.setDiet("diet1");
        Report report2 = new Report();
        report2.setId(2);
        report2.setDiet("diet2");

        List<Report> reports = List.of(
                report1,
                report2
        );

        when(reportRepository.findAllByIsViewedFalse()).thenReturn(reports);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/report/notViewed"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        [
                        {"id":1,"diet":"diet1"},
                        {"id":2,"diet":"diet2"}
                        ]"""));
        System.out.println();
    }

    @Test
    void markReport() throws Exception {
        Report report = new Report();
        report.setId(1);
        report.setDiet("diet");
        Report reportV = new Report();
        reportV.setId(1);
        reportV.setDiet("diet");
        reportV.setViewed(true);

        when(reportRepository.findById(any(Integer.class))).thenReturn(Optional.of(report));
        when(reportRepository.save(any(Report.class))).thenReturn(reportV);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/report/mark/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reportV.getId()))
                .andExpect(jsonPath("$.diet").value(reportV.getDiet()));
    }

    @Test
    void getReportById() throws Exception {
        Report report = new Report();
        report.setId(1);
        report.setDiet("diet");

        when(reportRepository.findById(anyInt())).thenReturn(Optional.of(report));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/report/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(report.getId()))
                .andExpect(jsonPath("$.diet").value(report.getDiet()));
    }

    @Test
    void messageByPetUser() throws Exception {
        int id = 1;
        String message = "text";
        MessageDto messageDto = new MessageDto();
        messageDto.setPetUserId(id);
        messageDto.setMessage(message);

        JSONObject messageObject = new JSONObject();
        messageObject.put("petUserId", messageDto.getPetUserId());
        messageObject.put("message", messageDto.getMessage());

        when(petUserRepository.findById(any(Integer.class))).thenReturn(Optional.of(new PetUser()));
        when(telegramBot.execute(any(SendMessage.class))).thenReturn(sendResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/report/message")
                        .content(messageObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
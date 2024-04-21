package ru.skypro.pets_home_bot.api_bot.controller;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.pets_home_bot.api_bot.dto.MessageDto;
import ru.skypro.pets_home_bot.api_bot.dto.ReportDto;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.api_bot.service.ReportService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;
    private final TelegramBot telegramBot;
    private final PetUserService petUserService;

    public ReportController(ReportService reportService, TelegramBot telegramBot, PetUserService petUserService) {
        this.reportService = reportService;
        this.telegramBot = telegramBot;
        this.petUserService = petUserService;
    }

    @Operation(
            summary = "Получения необработанных отчетов.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список всех необработанных отчетов"
                    )
            },
            tags = "Report")
    @GetMapping(value = "/notViewed")
    public ResponseEntity<List<ReportDto>> getNotViewedReports() {
        List<Report> reports = reportService.findAllByIsViewedFalse();
        if (reports == null || reports.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ReportDto> result = new ArrayList<>();
        for (Report report : reports) {
            ReportDto reportDto = new ReportDto(report);
            result.add(reportDto);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Отметки, что отчет просмотрен.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отмеченный отчет"
                    )
            },
            tags = "Report")
    @PutMapping(value = "/mark/{id}")
    public ResponseEntity<ReportDto> markReport(@PathVariable int id) {
        Optional<Report> reportOptional = reportService.findById(id);
        if (reportOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Report report = reportOptional.get();
        report.setViewed(true);
        reportService.add(report);
        ReportDto reportDto = new ReportDto(report);
        return ResponseEntity.ok(reportDto);
    }

    @Operation(
            summary = "Выгрузка отчета по его идентификатору.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Выгруженный отчет"
                    )
            },
            tags = "Report")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ReportDto> getReportById(@PathVariable int id) {
        Optional<Report> reportOptional = reportService.findById(id);
        return reportOptional
                .map(report -> ResponseEntity.ok(new ReportDto(report)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Отправка сообщения о ненадлежащем сданном отчете.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сообщение отправлено"
                    )
            },
            tags = "Report")
    @PostMapping(value = "/message")
    public ResponseEntity<?> messageByPetUser(@RequestBody MessageDto messageDto) {
        int petUserId = messageDto.getPetUserId();
        Optional<PetUser> petUserOptional = petUserService.findById(petUserId);
        if (petUserOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        long chatId = petUserOptional.get().getChatId();
        SendMessage sendMessage = new SendMessage(chatId, messageDto.getMessage());
        if (!telegramBot.execute(sendMessage).isOk()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}

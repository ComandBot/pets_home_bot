package ru.skypro.pets_home_bot.api_bot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.pets_home_bot.api_bot.model.Consultation;
import ru.skypro.pets_home_bot.api_bot.service.ConsultationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultation")
public class ConsultationsController {

    private final ConsultationService consultationService;

    public ConsultationsController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @Operation(
            summary = "Внесение консультации в базу данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Консультация добавлена"
                    )
            },
            tags = "Consultation")
    @PostMapping(value = "/add")
    ResponseEntity<Consultation> add(@RequestBody Consultation consultation) {
        return ResponseEntity.ok(consultationService.add(consultation));
    }

    @Operation(
            summary = "Поиск консультации по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Консультация найдена"
                    )
            },
            tags = "Consultation")
    @GetMapping(value = "/find/{id}")
    ResponseEntity<Consultation> findById(@PathVariable int id) {
        Optional<Consultation> optionalConsultation = consultationService.findConsultationById(id);
        return optionalConsultation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Поиск всех консультаций",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список всех консультаций"
                    )
            },
            tags = "Consultation")
    @GetMapping(value = "/all")
    ResponseEntity<List<Consultation>> findAll() {
        List<Consultation> consultations = consultationService.findAll();
        if (consultations == null || consultations.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consultations);
    }

    @Operation(
            summary = "Изменение консультации",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Консультация изменена"
                    )
            },
            tags = "Consultation")
    @PutMapping(value = "/edit")
    ResponseEntity<Consultation> editConsultation(@RequestBody Consultation consultation) {
        Optional<Consultation> optionalConsultation = consultationService.findConsultationById(consultation.getId());
        if (optionalConsultation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consultationService.add(consultation));
    }

    @Operation(
            summary = "Удаление консультации",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Консультация удалена"
                    )
            },
            tags = "Consultation")
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<Consultation> deleteById(@PathVariable int id) {
        Optional<Consultation> optionalConsultation = consultationService.findConsultationById(id);
        if (optionalConsultation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        consultationService.deleteById(id);
        return ResponseEntity.ok(optionalConsultation.get());
    }

}

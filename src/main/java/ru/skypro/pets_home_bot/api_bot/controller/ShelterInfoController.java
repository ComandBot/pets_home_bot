package ru.skypro.pets_home_bot.api_bot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.model.ShelterInfo;
import ru.skypro.pets_home_bot.api_bot.service.ShelterInfoService;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/info")
public class ShelterInfoController {

    private final ShelterInfoService shelterInfoService;
    private final ShelterService shelterService;

    public ShelterInfoController(ShelterInfoService shelterInfoService, ShelterService shelterService) {
        this.shelterInfoService = shelterInfoService;
        this.shelterService = shelterService;
    }

    @Operation(
            summary = "Добавление информации о приюте по его id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация добавлена"
                    )
            },
            tags = "ShelterInfo")
    @PostMapping(value = "/addInfo/{shelterId}")
    ResponseEntity<ShelterInfo> save(@RequestBody ShelterInfo shelterInfo, @PathVariable int shelterId) {
        Optional<Shelter> optionalShelter = shelterService.findByShelterId(shelterId);
        if (optionalShelter.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Shelter shelter = optionalShelter.get();
        shelterInfo.setShelter(shelter);
        shelterInfoService.save(shelterInfo);
        return ResponseEntity.ok(shelterInfo);
    }

    @Operation(
            summary = "Получение списка информаций о всех приютах",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список получен"
                    )
            },
            tags = "ShelterInfo")
    @GetMapping(value = "/all")
    ResponseEntity<List<ShelterInfo>> findAll() {
        return ResponseEntity.ok(shelterInfoService.findAll());
    }

    @Operation(
            summary = "Получение информации по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация по id получена"
                    )
            },
            tags = "ShelterInfo")
    @GetMapping(value = "/find/{id}")
    ResponseEntity<ShelterInfo> findById(@PathVariable int id) {
        Optional<ShelterInfo> shelterInfoOptional = shelterInfoService.findById(id);
        return shelterInfoOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Удаление информации по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация по id получена"
                    )
            },
            tags = "ShelterInfo")
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<ShelterInfo> deleteById(@PathVariable int id) {
        if (shelterInfoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        shelterInfoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Редактирование информации",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация отредактирована"
                    )
            },
            tags = "ShelterInfo")
    @PutMapping(value = "/edit")
    ResponseEntity<ShelterInfo> editShelterInfo(@RequestBody ShelterInfo shelterInfo) {
        if (shelterInfoService.findById(shelterInfo.getId()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shelterInfoService.save(shelterInfo));
    }
}

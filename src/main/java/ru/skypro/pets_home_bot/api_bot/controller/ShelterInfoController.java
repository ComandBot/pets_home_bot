package ru.skypro.pets_home_bot.api_bot.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.model.ShelterInfo;
import ru.skypro.pets_home_bot.api_bot.model_api.ShelterInfoApi;
import ru.skypro.pets_home_bot.api_bot.service.ShelterInfoService;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;

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

    @PostMapping(value = "/addApi")
    ShelterInfoApi save(@RequestBody ShelterInfoApi shelterInfoApi) {
        return shelterInfoApi;
    }

    @PostMapping(value = "/addInfo")
    ShelterInfo save(@RequestBody ShelterInfo shelterInfo) {
        return shelterInfo;
    }
}

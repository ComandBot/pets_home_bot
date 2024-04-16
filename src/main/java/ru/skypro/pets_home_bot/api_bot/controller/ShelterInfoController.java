package ru.skypro.pets_home_bot.api_bot.controller;

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

    @GetMapping(value = "/all")
    ResponseEntity<List<ShelterInfo>> findAll() {
        return ResponseEntity.ok(shelterInfoService.findAll());
    }

    @GetMapping(value = "/find/{id}")
    ResponseEntity<ShelterInfo> findById(@PathVariable int id) {
        Optional<ShelterInfo> shelterInfoOptional = shelterInfoService.findById(id);
        return shelterInfoOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<ShelterInfo> deleteById(@PathVariable int id) {
        if (shelterInfoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        shelterInfoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/edit")
    ResponseEntity<ShelterInfo> editShelterInfo(@RequestBody ShelterInfo shelterInfo) {
        if (shelterInfoService.findById(shelterInfo.getId()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shelterInfoService.save(shelterInfo));
    }
}

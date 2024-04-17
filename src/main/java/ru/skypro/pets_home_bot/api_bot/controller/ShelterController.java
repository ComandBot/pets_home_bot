package ru.skypro.pets_home_bot.api_bot.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shelter")
public class ShelterController {
    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Shelter> add(@RequestBody Shelter shelter) {
        return ResponseEntity.ok(shelterService.add(shelter));
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<Shelter> edit(@RequestBody Shelter shelter) {
        if (shelterService.findByShelterId(shelter.getId()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shelterService.add(shelter));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Shelter>> findAll() {
        List<Shelter> shelters = shelterService.findAll();
        if (shelters == null || shelters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shelters);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Shelter> deleteById(@PathVariable int id) {
        Optional<Shelter> shelterOptional = shelterService.findByShelterId(id);
        if (shelterOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        shelterService.deleteById(id);
        return ResponseEntity.ok(shelterOptional.get());
    }
}

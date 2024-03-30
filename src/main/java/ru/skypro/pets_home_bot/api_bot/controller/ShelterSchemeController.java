package ru.skypro.pets_home_bot.api_bot.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.pets_home_bot.api_bot.service.impl.ShelterSchemeServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("scheme")
public class ShelterSchemeController {

    private final ShelterSchemeServiceImpl shelterSchemeService;

    public ShelterSchemeController(ShelterSchemeServiceImpl shelterSchemeService) {
        this.shelterSchemeService = shelterSchemeService;
    }

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadScheme(@PathVariable int id, @RequestParam MultipartFile photo) throws IOException {
        shelterSchemeService.add(id, photo.getBytes());
        return ResponseEntity.ok().build();
    }
}

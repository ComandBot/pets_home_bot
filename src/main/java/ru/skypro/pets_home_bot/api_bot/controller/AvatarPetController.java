package ru.skypro.pets_home_bot.api_bot.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.pets_home_bot.api_bot.service.AvatarPetService;
import java.io.IOException;

@RestController
@RequestMapping("avatar")
public class AvatarPetController {
    private final AvatarPetService avatarPetService;

    public AvatarPetController(AvatarPetService avatarPetService) {
        this.avatarPetService = avatarPetService;
    }

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatarPet(@PathVariable int id,
                                                  @RequestParam MultipartFile avatar,
    @RequestParam String description) throws IOException {
        avatarPetService.save(id, description, avatar.getBytes());
        return ResponseEntity.ok().build();
    }
}

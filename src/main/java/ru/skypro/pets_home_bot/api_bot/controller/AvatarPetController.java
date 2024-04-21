package ru.skypro.pets_home_bot.api_bot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.pets_home_bot.api_bot.model.AvatarPet;
import ru.skypro.pets_home_bot.api_bot.service.AvatarPetService;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/avatar")
public class AvatarPetController {
    private final AvatarPetService avatarPetService;

    public AvatarPetController(AvatarPetService avatarPetService) {
        this.avatarPetService = avatarPetService;
    }

    @Operation(
            summary = "внесение аватара питомуа базу данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "аватар добавлен"
                    )
            },
            tags = "Avatar")
    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AvatarPet> uploadAvatarPet(@PathVariable int id,
                                                  @RequestParam MultipartFile avatar,
    @RequestParam String description) throws IOException {
        AvatarPet avatarPet = avatarPetService.save(id, description, avatar.getBytes());
        return ResponseEntity.ok(avatarPet);
    }

    @Operation(
            summary = "получение всех аватаров",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "питомец добавлен"
                    )
            },
            tags = "Avatar")
    @GetMapping("/all")
    public ResponseEntity<List<AvatarPet>> getAllAvatars() {
        List<AvatarPet> avatarPets = avatarPetService.getAvatarsPets();
        if(avatarPets == null || avatarPets.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(avatarPets);
    }
}

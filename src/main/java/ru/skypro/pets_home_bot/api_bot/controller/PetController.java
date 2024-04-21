package ru.skypro.pets_home_bot.api_bot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.service.PetService;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Operation(
            summary = "поиск питомца по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "найден питомец по id"
                    )
            },
            tags = "Pet")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Pet> getPetInfo(@PathVariable int id) {

        Optional<Pet> petOption = petService.findById(id);
        return petOption.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "поиск питомца по имени",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "найден питомец по имени"
                    )
            },
            tags = "Pet")
    @GetMapping("/name/{name}")
    public ResponseEntity<Pet> getPetName(@RequestBody String name) {

        Pet pet = petService.findByName(name);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pet);
    }

    @Operation(
            summary = "внесение питомца в базу данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "питомец добавлен"
                    )
            },
            tags = "Pet")
    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        return ResponseEntity.ok(petService.addPet(pet));
    }

    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "обновление информации"
            ),
            tags = "Pet"
    )
    @PutMapping
    public ResponseEntity<Pet> updatePet(@RequestBody Pet pet) {
        Optional<Pet> optionalPet = petService.findById(pet.getId());
        if (optionalPet.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(petService.addPet(pet));

    }

    @Operation(
            summary = "удаление питомца из базы данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "питомец удален"
                    )
            },
            tags = "Pet")
    @DeleteMapping("{id}")
    public ResponseEntity<Pet> deletePet(@PathVariable int id) {
        Optional<Pet> petOptional = petService.findById(id);
        if (petOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        petService.deleteById(id);
        return ResponseEntity.ok(petOptional.get());
    }
}

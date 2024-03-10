package ru.skypro.pets_home_bot.api_bot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.service.PetService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("pet")
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
            })
    @GetMapping("{id}")
    public ResponseEntity<Pet> getPetInfo(@PathVariable int id) {

        Pet pet = petService.getPet(id);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pet);
    }

    @Operation(
            summary = "поиск питомца по имени",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "найден питомец по имени"
                    )
            })
    @GetMapping("{name}")
    public ResponseEntity<Collection<Pet>> getPetName(@RequestBody String name) {

        Pet pet = (Pet) petService.findByName(name);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Operation(
            summary = "внесение питомца в базу данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "питомец добавлен"
                    )
            })
    @PostMapping
    public Pet createPet(@RequestBody Pet pet) {
        return petService.addPet(pet);
    }

    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "обновление информации"
            )
    )
    @PutMapping("{id}")
    public ResponseEntity<Pet> updatePet(@RequestBody Pet pet, @PathVariable int id) {
        Pet foundPet = petService.updatePet(pet);
        if (foundPet == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foundPet);

    }

    @Operation(
            summary = "удаление питомца из базы данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "питомец удален"
                    )
            })
    @DeleteMapping("{id}")
    public ResponseEntity deletePet(@PathVariable int id) {
        petService.deletePet(id);
        return ResponseEntity.ok().build();
    }
}

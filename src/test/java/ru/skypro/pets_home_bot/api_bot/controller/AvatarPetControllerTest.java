package ru.skypro.pets_home_bot.api_bot.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.pets_home_bot.api_bot.model.AvatarPet;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.repository.AvatarPetRepository;
import ru.skypro.pets_home_bot.api_bot.repository.PetRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.AvatarPetServiceImpl;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = AvatarPetController.class)
class AvatarPetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AvatarPetRepository avatarPetRepository;
    @MockBean
    private PetRepository petRepository;
    @SpyBean
    private AvatarPetServiceImpl avatarPetService;
    @InjectMocks
    private AvatarPetController avatarPetController;

    @Test
    void uploadAvatarPet() throws Exception {
        int id = 1;
        String description = "test";
        AvatarPet cat = new AvatarPet();
        cat.setId(id);
        cat.setDescription(description);

        when(avatarPetRepository.save(any(AvatarPet.class))).thenReturn(cat);
        when(petRepository.findById(any(Integer.class))).thenReturn(Optional.of(new Pet()));

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "avatar",
                Files.readAllBytes(Paths.get("data/cat.jpg"))
        );

        mockMvc.perform(MockMvcRequestBuilders.multipart("/avatar/1?description=text")
                .file(mockMultipartFile))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cat.getId()))
                .andExpect(jsonPath("$.description").value(cat.getDescription()));
    }

    @Test
    void getAllAvatars() throws Exception {
        int catId = 1;
        String descriptionCat = "cat";
        AvatarPet cat = new AvatarPet();
        cat.setId(catId);
        cat.setDescription(descriptionCat);

        int dogId = 2;
        String descriptionDog = "dog";
        AvatarPet dog = new AvatarPet();
        dog.setId(dogId);
        dog.setDescription(descriptionDog);

        List<AvatarPet> avatarPets = List.of(
                cat,
                dog
        );

        when(avatarPetRepository.findAll()).thenReturn(avatarPets);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/avatar/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        [
                        {"id":1,"description":"cat","pet":null},
                        {"id":2,"description":"dog","pet":null}
                        ]"""));
    }
}
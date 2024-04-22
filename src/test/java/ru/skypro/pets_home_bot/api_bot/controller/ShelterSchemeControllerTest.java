package ru.skypro.pets_home_bot.api_bot.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.pets_home_bot.api_bot.model.AvatarPet;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.model.ShelterScheme;
import ru.skypro.pets_home_bot.api_bot.repository.ShelterRepository;
import ru.skypro.pets_home_bot.api_bot.repository.ShelterSchemeRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.ShelterSchemeServiceImpl;
import ru.skypro.pets_home_bot.api_bot.service.impl.ShelterServiceImpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ShelterSchemeController.class)
class ShelterSchemeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShelterSchemeRepository shelterSchemeRepository;
    @MockBean
    private ShelterRepository shelterRepository;
    @SpyBean
    private ShelterServiceImpl shelterService;
    @SpyBean
    private ShelterSchemeServiceImpl shelterSchemeService;
    @InjectMocks
    private ShelterSchemeController shelterSchemeController;

    @Test
    void uploadScheme() throws Exception {
        int id = 1;
        ShelterScheme scheme = new ShelterScheme();
        scheme.setId(id);

        when(shelterSchemeRepository.save(any(ShelterScheme.class))).thenReturn(scheme);
        when(shelterRepository.findById(any(Integer.class))).thenReturn(Optional.of(new Shelter()));

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "photo",
                Files.readAllBytes(Paths.get("data/cat.jpg"))
        );

        mockMvc.perform(MockMvcRequestBuilders.multipart("/scheme/1")
                        .file(mockMultipartFile))
                .andExpect(status().isOk());
    }
}
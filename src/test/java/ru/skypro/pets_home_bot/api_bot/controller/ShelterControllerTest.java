package ru.skypro.pets_home_bot.api_bot.controller;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.repository.PetRepository;
import ru.skypro.pets_home_bot.api_bot.repository.ShelterRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.PetServiceImpl;
import ru.skypro.pets_home_bot.api_bot.service.impl.ShelterServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ShelterController.class)
class ShelterControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShelterRepository shelterRepository;
    @SpyBean
    private ShelterServiceImpl shelterService;
    @InjectMocks
    private ShelterController shelterController;

    @Test
    void add() throws Exception {
        int id = 1;
        String nameShelter = "Собачий";
        Shelter shelter = new Shelter();
        shelter.setNameShelter(nameShelter);
        shelter.setId(id);

        JSONObject shelterObject = new JSONObject();
        shelterObject.put("nameShelter", shelter.getNameShelter());

        when(shelterRepository.save(any())).thenReturn(shelter);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/shelter/add")
                        .content(shelterObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(shelter.getId()))
                .andExpect(jsonPath("$.nameShelter").value(shelter.getNameShelter()));
    }

    @Test
    void edit() throws Exception {
        int id = 1;
        String name = "Собачий";
        String newName = "Кошачий";
        JSONObject shelterObject = new JSONObject();
        shelterObject.put("id", id);
        shelterObject.put("nameShelter", newName);

        Shelter curShelter = new Shelter();
        curShelter.setId(id);
        curShelter.setNameShelter(name);
        Shelter newShelter = new Shelter();
        curShelter.setId(id);
        curShelter.setNameShelter(newName);

        when(shelterRepository.findById(any(Integer.class))).thenReturn(Optional.of(curShelter));
        when(shelterRepository.save(any(Shelter.class))).thenReturn(newShelter);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/shelter/edit")
                        .content(shelterObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nameShelter").value(newName));
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }
}
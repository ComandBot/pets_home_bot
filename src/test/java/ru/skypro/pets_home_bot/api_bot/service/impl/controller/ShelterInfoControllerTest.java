package ru.skypro.pets_home_bot.api_bot.service.impl.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.model.ShelterInfo;
import ru.skypro.pets_home_bot.api_bot.service.ShelterInfoService;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;

import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(ShelterInfoControllerTest.class)
public class ShelterInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShelterInfoService shelterInfoService;

    @MockBean
    private ShelterService shelterService;

    @Test
    public void testSave() throws Exception {
        ShelterInfo shelterInfo = new ShelterInfo();
        Shelter shelter = new Shelter();
        shelter.setId(1);
        shelterInfo.setShelter(shelter);

        when(shelterService.findByShelterId(1));

        mockMvc.perform(MockMvcRequestBuilders.post("/info/addInfo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"shelter\":\"1\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/info/all"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFindById() throws Exception {
        ShelterInfo shelterInfo = new ShelterInfo();
        shelterInfo.setId(1);

        Optional<ShelterInfo> optionalShelterInfo = Optional.of(shelterInfo);
        when(shelterInfoService.findById(1)).thenReturn(optionalShelterInfo);

        mockMvc.perform(MockMvcRequestBuilders.get("/info/find/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/info/delete/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
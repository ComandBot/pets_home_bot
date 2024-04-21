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
import ru.skypro.pets_home_bot.api_bot.repository.PetRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.PetServiceImpl;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PetRepository petRepository;
    @SpyBean
    private PetServiceImpl petService;
    @InjectMocks
    private PetController petController;
    @Test
    public void getPetByIdThenPetInfo() throws Exception {
        Pet pet = new Pet();
        pet.setId(1);
        pet.setName("Пушок");
        when(petRepository.findById(anyInt())).thenReturn(Optional.of(pet));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pet/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pet.getId()))
                .andExpect(jsonPath("$.name").value(pet.getName()));

    }

    @Test
    public void createPet() throws Exception {
        Pet pet = new Pet();
        pet.setName("Пушок");
        pet.setId(1);

        JSONObject petObject = new JSONObject();
        petObject.put("name", pet.getName());

        when(petRepository.save(any())).thenReturn(pet);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/pet")
                        .content(petObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pet.getId()))
                .andExpect(jsonPath("$.name").value(pet.getName()));
    }

    @Test
    public void updatePet() throws Exception {
        int id = 1;
        String name = "Bob";
        String newName = "Sharic";
        JSONObject petObject = new JSONObject();
        petObject.put("id", id);
        petObject.put("name", name);

        Pet curPet = new Pet();
        curPet.setId(id);
        curPet.setName(name);
        Pet newPet = new Pet();
        newPet.setId(id);
        newPet.setName(newName);

        when(petRepository.findById(any(Integer.class))).thenReturn(Optional.of(curPet));
        when(petRepository.save(any(Pet.class))).thenReturn(newPet);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/pet")
                        .content(petObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(newName));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        int id = 1;
        String name = "Bob";
        Pet pet = new Pet();
        pet.setId(1);
        pet.setName(name);

        when(petRepository.findById(any())).thenReturn(Optional.of(pet));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/pet/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }
}
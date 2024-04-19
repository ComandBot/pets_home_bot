package ru.skypro.pets_home_bot.api_bot.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.pets_home_bot.api_bot.enums.PetsTypes;
import ru.skypro.pets_home_bot.api_bot.repository.ConsultationRepository;
import ru.skypro.pets_home_bot.api_bot.repository.ShelterRepository;
import ru.skypro.pets_home_bot.api_bot.service.ConsultationService;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static ru.skypro.pets_home_bot.api_bot.enums.PetsTypes.CAT;

@WebMvcTest(controllers = ConsultationsController.class)
class ConsultationsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ConsultationRepository consultationRepository;
    @MockBean
    private ShelterRepository shelterRepository;
    @SpyBean
    private ConsultationService consultationService;
    @SpyBean
    private ShelterService shelterService;
    @InjectMocks
    private ConsultationsController consultationsController;

    private int consultationId = 1;

    private String typeConsultation = "Консультация";

    private String definition = "Бла-бла-бла";

    private int shelterId = 1;
    private String nameShelter = "Kotiki tut";
    private String description = "priyut dlya koshek";
    private PetsTypes petsTypes = CAT;


    @Test
    void add() throws Exception {
        //   Shelter shelter = new Shelter(shelterId, nameShelter, description, petsTypes);
//        shelter.setId(shelterId);
//        shelter.setNameShelter(nameShelter);
//        shelter.setDescription(description);
//        shelter.setPetTypes(petsTypes);
        //      JSONObject consultationObject = new JSONObject();
//        consultationObject.put("typeConsultation", typeConsultation);
//        consultationObject.put("definition", definition);
//        consultationObject.put("shelter", shelter);
//        JSONObject shelterObject = new JSONObject();
//        shelterObject.put("nameShelter", nameShelter)
//        shelterObject.put("description", description);
        //      shelterObject.put("petsTypes", petsTypes);

//        Consultation consultation = new Consultation(consultationId, typeConsultation, definition, shelter);
//
//
//        when(consultationRepository.save(any())).thenReturn(consultation);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/consultation")
//                        .content(consultationObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.typeConsultation").value(typeConsultation))
//                .andExpect(jsonPath("$.definition").value(definition));
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void editConsultation() {
    }

    @Test
    void deleteById() {
    }
}
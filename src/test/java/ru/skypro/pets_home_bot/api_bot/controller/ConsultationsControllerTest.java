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
import ru.skypro.pets_home_bot.api_bot.enums.PetsTypes;
import ru.skypro.pets_home_bot.api_bot.model.Consultation;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.repository.ConsultationRepository;
import ru.skypro.pets_home_bot.api_bot.repository.ShelterRepository;
import ru.skypro.pets_home_bot.api_bot.service.ConsultationService;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;
import ru.skypro.pets_home_bot.api_bot.service.impl.ConsultationServiceImpl;
import ru.skypro.pets_home_bot.api_bot.service.impl.ShelterServiceImpl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
    private ConsultationServiceImpl consultationService;
    @SpyBean
    private ShelterServiceImpl shelterService;
    @InjectMocks
    private ConsultationsController consultationsController;

    private int consultationId = 1;

    private String typeConsultation = "Консультация";

    private String definition = "Бла-бла-бла";

    private int consultationId2 = 2;
    private String typeConsultation2 = "Консультация2";

    private String definition2 = "Бла-бла-бла2";

    private int shelterId = 1;
    private String nameShelter = "Kotiki tut";
    private String description = "priyut dlya koshek";
    private PetsTypes petsTypes = CAT;


    @Test
    void add() throws Exception {
          Shelter shelter = new Shelter();
          shelter.setId(shelterId);
          shelter.setNameShelter(nameShelter);
          shelter.setDescription(description);
          shelter.setPetsTypes(petsTypes);
          JSONObject consultationObject = new JSONObject();
          consultationObject.put("id", consultationId);
          consultationObject.put("typeConsultation", typeConsultation);
          consultationObject.put("definition", definition);
          consultationObject.put("shelter", shelter);

          Consultation consultation = new Consultation();
          consultation.setId(consultationId);
          consultation.setTypeConsultation(typeConsultation);
          consultation.setDefinition(definition);
          consultation.setShelter(shelter);

          when(consultationRepository.save(any())).thenReturn(consultation);

         mockMvc.perform(MockMvcRequestBuilders
                          .post("/consultation/add")
                          .content(consultationObject.toString())
                          .contentType(MediaType.APPLICATION_JSON)
                          .accept(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.id").value(consultationId))
                  .andExpect(jsonPath("$.typeConsultation").value(typeConsultation))
                  .andExpect(jsonPath("$.definition").value(definition));
    }

    @Test
    void findById() throws Exception {
        Shelter shelter = new Shelter();
        shelter.setId(shelterId);
        shelter.setNameShelter(nameShelter);
        shelter.setDescription(description);
        shelter.setPetsTypes(petsTypes);
        JSONObject consultationObject = new JSONObject();
        consultationObject.put("id", consultationId);
        consultationObject.put("typeConsultation", typeConsultation);
        consultationObject.put("definition", definition);
        consultationObject.put("shelter", shelter);

        Consultation consultation = new Consultation();
        consultation.setId(consultationId);
        consultation.setTypeConsultation(typeConsultation);
        consultation.setDefinition(definition);
        consultation.setShelter(shelter);

        when(consultationRepository.findById(any(Integer.class))).thenReturn(Optional.of(consultation));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/consultation/find/1")
                        .content(consultationObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(consultation.getId()))
                .andExpect(jsonPath("$.typeConsultation").value(consultation.getTypeConsultation()))
                .andExpect(jsonPath("$.definition").value(consultation.getDefinition()));
    }

//    @Test
//    void findAll() throws Exception {
//
//        Shelter shelter = new Shelter();
//        shelter.setId(shelterId);
//        shelter.setNameShelter(nameShelter);
//        shelter.setDescription(description);
//        shelter.setPetsTypes(petsTypes);
//
//
//        Consultation consultation = new Consultation();
//        consultation.setId(consultationId);
//        consultation.setTypeConsultation(typeConsultation);
//        consultation.setDefinition(definition);
//        consultation.setShelter(shelter);
//
//        Consultation consultation2 = new Consultation();
//        consultation.setId(consultationId2);
//        consultation.setTypeConsultation(typeConsultation2);
//        consultation.setDefinition(definition2);
//        consultation.setShelter(shelter);
//        List<Consultation> cosultationsList = List.of(
//                consultation, consultation2
//        );
//        when(consultationRepository.findAll()).thenReturn(cosultationsList);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/consultation/all"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json("""
//                        [
//                        {"id":1,"typeConsultation":"Консультация","definition":"Бла-бла-бла","shelter":"Kotiki tut"},
//                        {"id":2,"typeConsultation":"Консультация2","definition":"Бла-бла-бла2","shelter":"Kotiki tut"}
//                        ]"""));
//
//    }

    @Test
    void editConsultation() {
    }

    @Test
    void deleteById() throws Exception {
        Shelter shelter = new Shelter();
        shelter.setId(shelterId);
        shelter.setNameShelter(nameShelter);
        shelter.setDescription(description);
        shelter.setPetsTypes(petsTypes);

        Consultation consultation = new Consultation();
        consultation.setId(consultationId);
        consultation.setTypeConsultation(typeConsultation);
        consultation.setDefinition(definition);
        consultation.setShelter(shelter);


        when(consultationRepository.findById(any(Integer.class))).thenReturn(Optional.of(consultation));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/consultation/delete/" + consultationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
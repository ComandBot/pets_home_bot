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
import ru.skypro.pets_home_bot.api_bot.service.impl.ConsultationServiceImpl;
import ru.skypro.pets_home_bot.api_bot.service.impl.ShelterServiceImpl;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        int id = 1;
        String typeConsultation = "type info";
        String definition = "definition";
        Consultation consultation = new Consultation();
        consultation.setId(id);
        consultation.setTypeConsultation(typeConsultation);
        consultation.setDefinition(definition);

        JSONObject shelterInfoObject = new JSONObject();
        shelterInfoObject.put("id", consultation.getId());
        shelterInfoObject.put("typeConsultation", consultation.getTypeConsultation());
        shelterInfoObject.put("definition", consultation.getDefinition());

        when(consultationRepository.findById(any(Integer.class))).thenReturn(Optional.of(consultation));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/consultation/find/1")
                        .content(shelterInfoObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(consultation.getId()))
                .andExpect(jsonPath("$.typeConsultation").value(consultation.getTypeConsultation()))
                .andExpect(jsonPath("$.definition").value(consultation.getDefinition()));
    }

    @Test
    void findAll() throws Exception {
        int id1 = 1;
        int id2 = 2;
        String typeConsultation1 = "typeConsultation1";
        String typeConsultation2 = "typeConsultation2";
        String definition1 = "Definition1";
        String definition2 = "Definition2";
        Consultation consultation1 = new Consultation();
        consultation1.setId(id1);
        consultation1.setTypeConsultation(typeConsultation1);
        consultation1.setDefinition(definition1);

        Consultation consultation2 = new Consultation();
        consultation2.setId(id2);
        consultation2.setTypeConsultation(typeConsultation2);
        consultation2.setDefinition(definition2);

        List<Consultation> consultations = List.of(
                consultation1,
                consultation2
        );

        when(consultationRepository.findAll()).thenReturn(consultations);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/consultation/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        [
                        {"id":1,"typeConsultation":"typeConsultation1","definition":"Definition1","shelter":null},
                        {"id":2,"typeConsultation":"typeConsultation2","definition":"Definition2","shelter":null}
                        ]"""));
    }

    @Test
    void editConsultation() throws Exception {
        int id = 1;
        String typeConsultation = "typeConsultation";
        String definition = "definition";
        Consultation consultation = new Consultation();
        consultation.setId(id);
        consultation.setTypeConsultation(typeConsultation);
        consultation.setDefinition(definition);

        String newTypeConsultation= "new typeConsultation";
        String newDefinition = "new definition";
        Consultation newConsultation = new Consultation();
        newConsultation.setId(id);
        newConsultation.setTypeConsultation(newTypeConsultation);
        newConsultation.setDefinition(newDefinition);

        JSONObject shelterInfoObject = new JSONObject();
        shelterInfoObject.put("id", newConsultation.getId());
        shelterInfoObject.put("typeConsultation", newConsultation.getTypeConsultation());
        shelterInfoObject.put("definition", newConsultation.getDefinition());

        when(consultationRepository.findById(any(Integer.class))).thenReturn(Optional.of(consultation));
        when(consultationRepository.save(any(Consultation.class))).thenReturn(newConsultation);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/consultation/edit")
                        .content(shelterInfoObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newConsultation.getId()))
                .andExpect(jsonPath("$.typeConsultation").value(newConsultation.getTypeConsultation()))
                .andExpect(jsonPath("$.definition").value(newConsultation.getDefinition()));
    }

    @Test
    void deleteById() throws Exception {
        int id = 1;
        String typeConsultation = "typeConsultation";
        String definition = "definition";
        Consultation consultation = new Consultation();
        consultation.setId(id);
        consultation.setTypeConsultation(typeConsultation);
        consultation.setDefinition(definition);

        when(consultationRepository.findById(any(Integer.class))).thenReturn(Optional.of(consultation));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/consultation/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
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
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.model.ShelterInfo;
import ru.skypro.pets_home_bot.api_bot.repository.ShelterInfoRepository;
import ru.skypro.pets_home_bot.api_bot.repository.ShelterRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.ShelterInfoServiceImpl;
import ru.skypro.pets_home_bot.api_bot.service.impl.ShelterServiceImpl;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = ShelterInfoController.class)
class ShelterInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShelterInfoRepository shelterInfoRepository;
    @MockBean
    private ShelterRepository shelterRepository;
    @SpyBean
    private ShelterServiceImpl shelterService;
    @SpyBean
    private ShelterInfoServiceImpl shelterInfoService;
    @InjectMocks
    private ShelterInfoController shelterInfoController;

    @Test
    void save() throws Exception {
        int id = 1;
        String typeInfo = "type info";
        String definition = "definition";
        ShelterInfo shelterInfo = new ShelterInfo();
        shelterInfo.setId(id);
        shelterInfo.setTypeInfo(typeInfo);
        shelterInfo.setDefinition(definition);

        JSONObject shelterInfoObject = new JSONObject();
        shelterInfoObject.put("id", shelterInfo.getId());
        shelterInfoObject.put("typeInfo", shelterInfo.getTypeInfo());
        shelterInfoObject.put("definition", shelterInfo.getDefinition());

        when(shelterRepository.findById(any(Integer.class))).thenReturn(Optional.of(new Shelter()));
        when(shelterInfoRepository.save(any(ShelterInfo.class))).thenReturn(shelterInfo);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/info/addInfo/1")
                        .content(shelterInfoObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(shelterInfo.getId()))
                .andExpect(jsonPath("$.typeInfo").value(shelterInfo.getTypeInfo()))
                .andExpect(jsonPath("$.definition").value(shelterInfo.getDefinition()));
    }

    @Test
    void findAll() throws Exception {
        int id1 = 1;
        int id2 = 2;
        String typeInfo1 = "TypeInfo1";
        String typeInfo2 = "TypeInfo2";
        String definition1 = "Definition1";
        String definition2 = "Definition2";
        ShelterInfo shelterInfo1 = new ShelterInfo();
        shelterInfo1.setId(id1);
        shelterInfo1.setTypeInfo(typeInfo1);
        shelterInfo1.setDefinition(definition1);

        ShelterInfo shelterInfo2 = new ShelterInfo();
        shelterInfo2.setId(id2);
        shelterInfo2.setTypeInfo(typeInfo2);
        shelterInfo2.setDefinition(definition2);

        List<ShelterInfo> shelterInfos = List.of(
                shelterInfo1,
                shelterInfo2
        );

        when(shelterInfoRepository.findAll()).thenReturn(shelterInfos);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/info/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        [
                        {"id":1,"typeInfo":"TypeInfo1","definition":"Definition1","shelter":null},
                        {"id":2,"typeInfo":"TypeInfo2","definition":"Definition2","shelter":null}
                        ]"""));
    }

    @Test
    void findById() throws Exception {
        int id = 1;
        String typeInfo = "type info";
        String definition = "definition";
        ShelterInfo shelterInfo = new ShelterInfo();
        shelterInfo.setId(id);
        shelterInfo.setTypeInfo(typeInfo);
        shelterInfo.setDefinition(definition);

        JSONObject shelterInfoObject = new JSONObject();
        shelterInfoObject.put("id", shelterInfo.getId());
        shelterInfoObject.put("typeInfo", shelterInfo.getTypeInfo());
        shelterInfoObject.put("definition", shelterInfo.getDefinition());

        when(shelterInfoRepository.findById(any(Integer.class))).thenReturn(Optional.of(shelterInfo));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/info/find/1")
                        .content(shelterInfoObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(shelterInfo.getId()))
                .andExpect(jsonPath("$.typeInfo").value(shelterInfo.getTypeInfo()))
                .andExpect(jsonPath("$.definition").value(shelterInfo.getDefinition()));
    }

    @Test
    void deleteById() throws Exception {
        int id = 1;
        String typeInfo = "type info";
        String definition = "definition";
        ShelterInfo shelterInfo = new ShelterInfo();
        shelterInfo.setId(id);
        shelterInfo.setTypeInfo(typeInfo);
        shelterInfo.setDefinition(definition);

        when(shelterInfoRepository.findById(any(Integer.class))).thenReturn(Optional.of(shelterInfo));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/info/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void editShelterInfo() throws Exception {
        int id = 1;
        String typeInfo = "type info";
        String definition = "definition";
        ShelterInfo shelterInfo = new ShelterInfo();
        shelterInfo.setId(id);
        shelterInfo.setTypeInfo(typeInfo);
        shelterInfo.setDefinition(definition);

        String newTypeInfo = "new type info";
        String newDefinition = "new definition";
        ShelterInfo newShelterInfo = new ShelterInfo();
        newShelterInfo.setId(id);
        newShelterInfo.setTypeInfo(newTypeInfo);
        newShelterInfo.setDefinition(newDefinition);

        JSONObject shelterInfoObject = new JSONObject();
        shelterInfoObject.put("id", newShelterInfo.getId());
        shelterInfoObject.put("typeInfo", newShelterInfo.getTypeInfo());
        shelterInfoObject.put("definition", newShelterInfo.getDefinition());

        when(shelterInfoRepository.findById(any(Integer.class))).thenReturn(Optional.of(shelterInfo));
        when(shelterInfoRepository.save(any(ShelterInfo.class))).thenReturn(newShelterInfo);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/info/edit")
                        .content(shelterInfoObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newShelterInfo.getId()))
                .andExpect(jsonPath("$.typeInfo").value(newShelterInfo.getTypeInfo()))
                .andExpect(jsonPath("$.definition").value(newShelterInfo.getDefinition()));

    }
}
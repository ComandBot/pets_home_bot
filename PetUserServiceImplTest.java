package ru.skypro.pets_home_bot.api_bot.service.impl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.repository.PetUserRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.PetUserServiceImpl;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetUserServiceImplTest {

    @InjectMocks
    private PetUserServiceImpl out;
    @Mock
    private PetUserRepository petUserRepository;

    @Test
    public void addTest() {
        PetUser petUser = new PetUser();
        petUser.setId(1);
        when(petUserRepository.save(petUser)).thenReturn(petUser);
        PetUser result = out.add(petUser);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }


    @Test
    public void findByChatIdPetUserTest() {
        PetUser petUser = new PetUser();
        petUser.setId(1);
        when(petUserRepository.findByChatId(anyLong())).thenReturn(petUser);
        PetUser result = out.findByChatIdPetUser(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    public void findByChatIdPetUserNotFoundTest() {
        when(petUserRepository.findByChatId(anyLong())).thenReturn(null);
        PetUser result = out.findByChatIdPetUser(1);
        assertNull(result);
    }
}
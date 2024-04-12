package ru.skypro.pets_home_bot.api_bot.service.impl.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.repository.VolunteerRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.VolunteerServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VolunteerServiceImplTest {

    @Mock
    private VolunteerRepository volunteerRepository;

    @InjectMocks
    private VolunteerServiceImpl volunteerService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddVolunteer() {
        Volunteer volunteer = new Volunteer();
        when(volunteerRepository.save(any(Volunteer.class))).thenReturn(volunteer);

        Volunteer result = volunteerService.add(volunteer);

        assertEquals(volunteer, result);
        verify(volunteerRepository).save(any(Volunteer.class));
    }

    @Test
    public void testDeleteVolunteer() {
        volunteerService.delete(1);

        verify(volunteerRepository).deleteById(1);
    }

    @Test
    public void testSetWorkPetUser() {
        Volunteer volunteer = new Volunteer();
        PetUser petUser = new PetUser();

        when(volunteerRepository.findById((long) anyInt())).thenReturn(java.util.Optional.of(volunteer));

        Volunteer result = volunteerService.setWorkPetUser(1, petUser);

        assertEquals(volunteer, result);
        verify(volunteerRepository).findById(1L);
        verify(volunteerRepository).save(any(Volunteer.class));
    }

    @Test
    public void testRemoveWorkPetUser() {
        Volunteer volunteer = new Volunteer();
        volunteer.setWorkUserId(new PetUser());

        when(volunteerRepository.findById((long) anyInt())).thenReturn(Optional.of(volunteer));

        Volunteer result = volunteerService.removeWorkPetUser(1);

        assertEquals(volunteer, result);
        verify(volunteerRepository).findById(1L);
        verify(volunteerRepository).save(any(Volunteer.class));
    }

    @Test
    public void testFindByChatIdVolunteer() {
        Volunteer volunteer = new Volunteer();
        volunteer.setChatId(123L);

        when(volunteerRepository.findByChatId(anyLong())).thenReturn(volunteer);

        Volunteer result = volunteerService.findByChatIdVolunteer(123L);

        assertEquals(volunteer, result);
        verify(volunteerRepository).findByChatId(123L);
    }

    @Test
    public void testFindByChatIdVolunteerNotFound() {
        when(volunteerRepository.findByChatId(anyLong())).thenReturn(null);

        Volunteer result = volunteerService.findByChatIdVolunteer(123L);

        assertNull(result);
        verify(volunteerRepository).findByChatId(123L);
    }
}
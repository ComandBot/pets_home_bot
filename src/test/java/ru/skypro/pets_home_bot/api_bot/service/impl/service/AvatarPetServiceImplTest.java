package ru.skypro.pets_home_bot.api_bot.service.impl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.pets_home_bot.api_bot.model.AvatarPet;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.repository.AvatarPetRepository;
import ru.skypro.pets_home_bot.api_bot.repository.PetRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.AvatarPetServiceImpl;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AvatarPetServiceImplTest {

    @InjectMocks
    private AvatarPetServiceImpl out;
    @Mock
    private AvatarPetRepository avatarPetRepository;
    @Mock
    private PetRepository petRepository;

    @Test
    public void saveTest() {
        Pet pet = new Pet();
        pet.setId(1);
        when(petRepository.findById(anyInt())).thenReturn(Optional.of(pet));
        AvatarPet avatarPet = out.save(1, "description", new byte[]{1, 2, 3});
        assertNotNull(avatarPet);
        assertEquals(1, avatarPet.getPet().getId());
        assertEquals("description", avatarPet.getDescription());
    }

    @Test
    public void savePetNotFoundTest() {
        when(petRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> out.save(1, "description", new byte[]{1, 2, 3}));
    }

    @Test
    public void findAvatarPetByPetIdTest() {
        Pet pet = new Pet();
        pet.setId(1);
        when(petRepository.findById(anyInt())).thenReturn(Optional.of(pet));
        AvatarPet avatarPet = new AvatarPet();
        avatarPet.setId(1);
        avatarPet.setPet(pet);
        when(avatarPetRepository.findByPetId(anyInt())).thenReturn(avatarPet);
        Optional<AvatarPet> result = out.findAvatarPetByPetId(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        assertEquals(1, result.get().getPet().getId());
    }

    @Test
    public void findAvatarPetByPetIdPetNotFoundTest() {
        when(petRepository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<AvatarPet> result = out.findAvatarPetByPetId(1);
        assertFalse(result.isPresent());
    }

    @Test
    public void findAvatarPetByPetIdAvatarNotFoundTest() {
        Pet pet = new Pet();
        pet.setId(1);
        when(petRepository.findById(anyInt())).thenReturn(Optional.of(pet));
        when(avatarPetRepository.findByPetId(anyInt())).thenReturn(null);
        Optional<AvatarPet> result = out.findAvatarPetByPetId(1);
        assertFalse(result.isPresent());
    }
}
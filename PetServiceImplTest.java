package ru.skypro.pets_home_bot.api_bot.service.impl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.repository.PetRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.PetServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetServiceImplTest {

    @InjectMocks
    private PetServiceImpl out;
    @Mock
    private PetRepository petRepository;

    @Test
    public void addPetTest() {
        Pet pet = new Pet();
        pet.setId(1);
        when(petRepository.save(pet)).thenReturn(pet);
        Pet result = out.addPet(pet);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    public void updatePetTest() {
        Pet pet = new Pet();
        pet.setId(1);
        when(petRepository.save(pet)).thenReturn(pet);
        Pet result = out.updatePet(pet);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    public void deletePetTest() {
        out.deletePet(1);
    }

    @Test
    public void findPetsByShelterIdTest() {
        List<Pet> pets = List.of(new Pet(), new Pet());
        when(petRepository.findAllByShelterId(anyInt())).thenReturn(pets);
        List<Pet> result = out.findPetsByShelterId(1);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void findByNameTest() {
        List<Pet> pets = List.of(new Pet(), new Pet());
        when(petRepository.findByName(anyString())).thenReturn(pets);
        Collection<Pet> result = out.findByName("name");
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void findByIdTest() {
        Pet pet = new Pet();
        pet.setId(1);
        when(petRepository.findById(anyInt())).thenReturn(Optional.of(pet));
        Optional<Pet> result = out.findById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    public void findByIdNotFoundTest() {
        when(petRepository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<Pet> result = out.findById(1);
        assertFalse(result.isPresent());
    }

    @Test
    public void findAllByShelterAndIdInOwnerTest() {
        List<Pet> pets = List.of(new Pet(), new Pet());
        when(petRepository.findAllByShelterAndIdInOwner(anyInt())).thenReturn(pets);
        List<Pet> result = out.findAllByShelterAndIdInOwner(1);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void findByReportPetsListTest() {
        List<Pet> pets = List.of(new Pet(), new Pet());
        when(petRepository.findByReportPetsList(anyInt())).thenReturn(pets);
        List<Pet> result = out.findByReportPetsList(1);
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
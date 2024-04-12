package ru.skypro.pets_home_bot.api_bot.service.impl.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.pets_home_bot.api_bot.enums.PetsTypes;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.repository.ShelterRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.ShelterServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShelterServiceImplTest {

    @Mock
    private ShelterRepository shelterRepository;

    @InjectMocks
    private ShelterServiceImpl shelterService;

    private Shelter shelter;

    @BeforeEach
    public void setUp() {
        shelter = new Shelter();
        shelter.setId(1);
        shelter.setNameShelter("Shelter 1");
        shelter.setId(Integer.parseInt("1"));
        shelter.setPetsTypes(PetsTypes.CAT);
    }

    @Test
    public void findByCatShelters_shouldReturnListOfCatShelters() {
        when(shelterRepository.findSheltersByPetsTypes(PetsTypes.CAT)).thenReturn(List.of(shelter));

        List<Shelter> catShelters = shelterService.findByCatShelters();

        assertThat(catShelters).hasSize(1);
        assertThat(catShelters.get(0)).isEqualTo(shelter);
    }

    @Test
    public void findByDogShelters_shouldReturnListOfDogShelters() {
        when(shelterRepository.findSheltersByPetsTypes(PetsTypes.DOG)).thenReturn(List.of(shelter));

        List<Shelter> dogShelters = shelterService.findByDogShelters();

        assertThat(dogShelters).hasSize(1);
        assertThat(dogShelters.get(0)).isEqualTo(shelter);
    }

    @Test
    public void findByShelterId_shouldReturnOptionalOfShelter() {
        when(shelterRepository.findById(1)).thenReturn(Optional.of(shelter));

        Optional<Shelter> optionalShelter = shelterService.findByShelterId(1);

        assertThat(optionalShelter).isPresent();
        assertThat(optionalShelter.get()).isEqualTo(shelter);
    }

    @Test
    public void findByShelterId_shouldReturnEmptyOptional_whenShelterNotFound() {
        when(shelterRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Shelter> optionalShelter = shelterService.findByShelterId(1);

        assertThat(optionalShelter).isEmpty();
    }
}
package ru.skypro.pets_home_bot.api_bot.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.repository.PetRepository;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceImplTest {
    @InjectMocks
    private PetServiceImpl out;
    @Mock
    PetRepository petRepositoryMock;


    @BeforeEach
    void setUp() {
        out = new PetServiceImpl(petRepositoryMock);

    }

    @Test
    void addPet() {
        Pet expected = new Pet();
        expected.setId(1);
        expected.setName("Vasya");
        when(petRepositoryMock.save(any(Pet.class))).thenReturn(expected);

        Pet result = out.addPet(expected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getPet() {
        Pet expected = new Pet();
        expected.setId(1);
        expected.setName("Vasya");
        when(petRepositoryMock.save(any(Pet.class))).thenReturn(expected);

        Pet result = out.addPet(expected);

        assertThat(result.getId()).isSameAs(expected.getId());
    }

    @Test
    void updatePet() {
        int petId = 2;
        String newName = "Pushok";
        Pet expected = new Pet();
        expected.setId(1);
        expected.setName("Vasya");
        when(petRepositoryMock.save(any(Pet.class))).thenReturn(expected);

        Pet result = out.addPet(expected);
        expected.setName(newName);
        expected.setId(petId);
        Pet result2 = out.updatePet(expected); //не уверена, что это проверяет работу метода
        assertThat(result).isEqualTo(result2);

    }

    @Test
    void deletePet() {
        Pet expected = new Pet();
        expected.setId(1);
        expected.setName("Vasya");
        when(petRepositoryMock.save(any(Pet.class))).thenReturn(expected);

        Pet result = out.addPet(expected);
        out.deleteById(result.getId());
        verify(petRepositoryMock, times(1)).deleteById(eq(1));
    }
}
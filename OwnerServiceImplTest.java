package ru.skypro.pets_home_bot.api_bot.service.impl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.OwnerId;
import ru.skypro.pets_home_bot.api_bot.repository.OwnerRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.OwnerServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceImplTest {

    @InjectMocks
    private OwnerServiceImpl out;
    @Mock
    private OwnerRepository ownerRepository;

    @Test
    public void addOwnerTest() {
        Owner owner = new Owner();
        owner.setOwnerId(1);
        when(ownerRepository.save(owner)).thenReturn(owner);
        Owner result = out.addOwner(owner);
        assertNotNull(result);
        assertEquals(1, result.getClass());
    }

    @Test
    public void findByOwnerIdTest() {
        Owner owner = new Owner();
        owner.setOwnerId(1);
        when(ownerRepository.findByOwnerId(any(OwnerId.class))).thenReturn(Optional.of(owner));
        Optional<Owner> result = out.findByOwnerId(new OwnerId());
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getClass());
    }

    @Test
    public void findByOwnerIdNotFoundTest() {
        when(ownerRepository.findByOwnerId(any(OwnerId.class))).thenReturn(Optional.empty());
        Optional<Owner> result = out.findByOwnerId(new OwnerId());
        assertFalse(result.isPresent());
    }

    @Test
    public void findByPetIdAndPetUserIdTest() {
        Owner owner = new Owner();
        owner.setOwnerId(1);
        when(ownerRepository.findByPetIdAndPetUserId(anyInt(), anyInt())).thenReturn(Optional.of(owner));
        Optional<Owner> result = out.findByPetIdAndPetUserId(1, 1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getClass());
    }

    @Test
    public void findByPetIdAndPetUserIdNotFoundTest() {
        when(ownerRepository.findByPetIdAndPetUserId(anyInt(), anyInt())).thenReturn(Optional.empty());
        Optional<Owner> result = out.findByPetIdAndPetUserId(1, 1);
        assertFalse(result.isPresent());
    }
}
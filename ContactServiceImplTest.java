package ru.skypro.pets_home_bot.api_bot.service.impl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.pets_home_bot.api_bot.model.Contact;
import ru.skypro.pets_home_bot.api_bot.repository.ContactRepository;
import ru.skypro.pets_home_bot.api_bot.service.impl.ContactServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTest {

    @InjectMocks
    private ContactServiceImpl out;
    @Mock
    private ContactRepository contactRepository;

    @Test
    public void addTest() {
        Contact contact = new Contact();
        contact.setId(1);
        when(contactRepository.save(contact)).thenReturn(contact);
        Contact result = out.add(contact);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }
}
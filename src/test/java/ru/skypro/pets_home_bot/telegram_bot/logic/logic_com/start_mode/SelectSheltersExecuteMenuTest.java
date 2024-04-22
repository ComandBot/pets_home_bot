package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SelectSheltersExecuteMenuTest {
    long id = 123456;
    private Update update;

    @Mock
    private PetUserService petUserService;
    @InjectMocks
    private SelectSheltersExecuteMenu selectSheltersExecuteMenu;

    @BeforeEach
    public void initMock() {
        Chat chat = Mockito.spy(Chat.class);
        ReflectionTestUtils.setField(chat, "id", id);

        Message message = Mockito.spy(Message.class);
        ReflectionTestUtils.setField(message, "chat", chat);

        update = new Update();
        ReflectionTestUtils.setField(update, "message", message);
    }

    @Test
    void whenExecuteThenTextAndChatId() {
        when(petUserService.add(any())).thenReturn(new PetUser());
        SendMessage sendMessage = (SendMessage) (selectSheltersExecuteMenu.execute(update));
        String menu = """
            Добро пожаловать.
            Меню выбора приюта:
            %s - показать список приютов для котов
            %s - показать список приютов для собак
            """;

        String expect = String.format(menu, Link.SHELTERS_CATS, Link.SHELTERS_DOGS);
        assertThat(expect).isEqualTo(sendMessage.getParameters().get("text"));
        assertThat(id).isEqualTo(sendMessage.getParameters().get("chat_id"));
    }
}

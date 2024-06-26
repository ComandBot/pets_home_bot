package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;

@Component
public class StartExecuteMenu implements ExecuteMessage {

    private final String menu = """
            Меню авторизаци и приветствие:
            %s - войти как усыновитель
            %s - войти как волонтер
            """;

    private final PetUserService petUserService;
    private final VolunteerService volunteerService;

    private final SelectSheltersExecuteMenu sheltersExecuteMenu;
    private final VolunteerExecuteMenu volunteerExecuteMenu;

    public StartExecuteMenu(PetUserService petUserService, VolunteerService volunteerService, SelectSheltersExecuteMenu sheltersExecuteMenu, VolunteerExecuteMenu volunteerExecuteMenu) {
        this.petUserService = petUserService;
        this.volunteerService = volunteerService;
        this.sheltersExecuteMenu = sheltersExecuteMenu;
        this.volunteerExecuteMenu = volunteerExecuteMenu;
    }


    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        return new SendMessage(chatId, String.format(menu, PET_USER, VOLUNTEER));
    }

    @Override
    public String getLink() {
        return START;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.START;
    }
}

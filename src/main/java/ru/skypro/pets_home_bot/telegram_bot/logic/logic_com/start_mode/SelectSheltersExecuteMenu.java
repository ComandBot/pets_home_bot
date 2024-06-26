package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;
@Component
public class SelectSheltersExecuteMenu implements ExecuteMessage {
    private final String menu = """
            Добро пожаловать.
            Меню выбора приюта:
            %s - показать список приютов для котов
            %s - показать список приютов для собак
            """;
    private final PetUserService petUserService;

    public SelectSheltersExecuteMenu(PetUserService petUserService) {
        this.petUserService = petUserService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = new PetUser();
        petUser.setChatId(chatId);
        petUser.setMessageMode(MessageMode.DEFAULT);
        petUser.setFirstName(update.message().chat().firstName());
        petUser.setLastName(update.message().chat().lastName());
        petUser.setUserName(update.message().chat().username());
        petUserService.add(petUser);
        return new SendMessage(chatId, String.format(menu, SHELTERS_CATS, SHELTERS_DOGS));
    }

    @Override
    public String getLink() {
        return PET_USER;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.START;
    }
}

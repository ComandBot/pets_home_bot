package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.default_mode;

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
            Меню выбора приюта:
            %s - показать список приютов для котов
            %s - показать список приютов для собак
            """;
    private final PetUserService petUserService;
    private final VolunteerService volunteerService;

    public SelectSheltersExecuteMenu(PetUserService petUserService, VolunteerService volunteerService) {
        this.petUserService = petUserService;
        this.volunteerService = volunteerService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        String text = update.message().text();
        if (petUser != null && PET_USER.equals(text)) {
            return new SendMessage(chatId, "Вы уже зарегестрированы как усыновитель");
        }
        if (petUser == null) {
            petUser = new PetUser();
            petUser.setChatId(chatId);
            petUser.setMessageMode(MessageMode.DEFAULT);
            petUserService.add(petUser);
        }
        return new SendMessage(chatId, String.format(menu, SHELTERS_CATS, SHELTERS_DOGS));
    }

    @Override
    public String getLink() {
        return PET_USER;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }
}

package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode.communication_user_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.ANSWER_PET_USER;
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.DEFAULT_TEXT;
@Component
public class MessageVolunteerExecute implements ExecuteMessage {
    private final String template = """
            Сообщение от пользователя. 
            Для ответа нажмите %s
            ----------------------------------------
            """;
    private final PetUserService petUserService;
    private final VolunteerService volunteerService;

    public MessageVolunteerExecute(PetUserService petUserService, VolunteerService volunteerService) {
        this.petUserService = petUserService;
        this.volunteerService = volunteerService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Пользователь не существует");
        }
        Volunteer volunteer = volunteerService.findByWorkUserId(petUser);
        if (volunteer == null) {
            return new SendMessage(chatId, "Связь с волонтером не установлена");
        }
        String text = update.message().text();
        if (!MessageMode.COMMUNICATION_VOLUNTEER.equals(volunteer.getMessageMode())) {
            text = String.format(template + text, ANSWER_PET_USER);
        }
        return new SendMessage(volunteer.getChatId(), text);
    }

    @Override
    public String getLink() {
        return DEFAULT_TEXT;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.COMMUNICATION_USER;
    }
}

package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode.communication_volunteer_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.CANSEL_COMMUNICATION;
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.DEFAULT_TEXT;
@Component
public class MessagePetUserExecute implements ExecuteMessage {
    private final VolunteerService volunteerService;

    public MessagePetUserExecute(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        Volunteer volunteer = volunteerService.findByChatIdVolunteer(chatId);
        if (volunteer == null) {
            return new SendMessage(chatId, "Волонтер не зарегестрирован");
        }
        PetUser petUser = volunteer.getWorkUserId();
        if (petUser == null) {
            return new SendMessage(chatId, "Волонтер больше не общается с усыновителем. " +
                    "Ожидайте нового запроса.\n" +
                    "Для выхода из режима общения нажмите " + CANSEL_COMMUNICATION);
        }
        long chatIdPetUser = petUser.getChatId();
        String text = update.message().text();
        return new SendMessage(chatIdPetUser, text);
    }

    @Override
    public String getLink() {
        return DEFAULT_TEXT;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.COMMUNICATION_VOLUNTEER;
    }
}

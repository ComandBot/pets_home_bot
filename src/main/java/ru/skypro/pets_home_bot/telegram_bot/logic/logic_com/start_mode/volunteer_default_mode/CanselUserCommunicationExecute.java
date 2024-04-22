package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.CANSEL_USER_COMMUNICATION;

@Component
public class CanselUserCommunicationExecute implements ExecuteMessage {
    private final VolunteerService volunteerService;

    public CanselUserCommunicationExecute(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        Volunteer volunteer = volunteerService.findByChatIdVolunteer(chatId);
        if (volunteer == null) {
            return new SendMessage(chatId, "Волонтер не зарегестрирован");
        }
        if (volunteer.getWorkUserId() == null) {
            return new SendMessage(chatId, "Волонтер не общается с пользователем");
        }
        volunteer.setWorkUserId(null);
        volunteerService.add(volunteer);
        return new SendMessage(chatId, "Вы прекратили общение с пользователем");
    }

    @Override
    public String getLink() {
        return CANSEL_USER_COMMUNICATION;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

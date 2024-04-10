package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode.communication_volunteer_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.CANSEL_COMMUNICATION;

@Component
public class ExitCommunicationVolunteerExecute implements ExecuteMessage {

    private final VolunteerService volunteerService;

    public ExitCommunicationVolunteerExecute(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        Volunteer volunteer = volunteerService.findByChatIdVolunteer(chatId);
        if (volunteer == null) {
            return new SendMessage(chatId, "Волонтер не зарегестрирован");
        }
        volunteer.setMessageMode(MessageMode.VOLUNTEER_DEFAULT);
        volunteerService.add(volunteer);
        return new SendMessage(chatId, "Вы вышли из режима общения с пользователем");
    }

    @Override
    public String getLink() {
        return CANSEL_COMMUNICATION;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.COMMUNICATION_VOLUNTEER;
    }
}

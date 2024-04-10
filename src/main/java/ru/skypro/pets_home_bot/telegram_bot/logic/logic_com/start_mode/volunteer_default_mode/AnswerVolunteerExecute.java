package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.ANSWER_PET_USER;
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.CANSEL_COMMUNICATION;

@Component
public class AnswerVolunteerExecute implements ExecuteMessage {
    private final String template = """
            Напишите сообщение пользователю.
            %s - прекратить общение
            """;
    private final VolunteerService volunteerService;

    public AnswerVolunteerExecute(VolunteerService volunteerService) {
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
            return new SendMessage(chatId, "Никакой усыновитель не запрашивал помощь");
        }
        volunteer.setMessageMode(MessageMode.COMMUNICATION_VOLUNTEER);
        volunteerService.add(volunteer);
        String answer = String.format(template, CANSEL_COMMUNICATION);
        return new SendMessage(chatId, answer);
    }

    @Override
    public String getLink() {
        return ANSWER_PET_USER;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

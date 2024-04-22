package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.SOLUTION;
@Component
public class VolunteerMenuExecute implements ExecuteMessage {
    private final String menu = """
            Меню волонтера:
            %s - показать отчеты владельцев
            %s - показать заявки на усыновление
            %s - принятие решения по владельцам
            %s
            """;

    private final VolunteerService volunteerService;

    public VolunteerMenuExecute(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        Volunteer volunteer = volunteerService.findByChatIdVolunteer(chatId);
        String text = "На данный момент вы не общаетесь с пользователем";
        if (volunteer != null && volunteer.getWorkUserId() != null) {
            String textTemp = """
                    %s - написать пользователю
                    %s - завершить общение с пользователем
                    """;
            text = String.format(textTemp, ANSWER_PET_USER, CANSEL_USER_COMMUNICATION);
        }
        String answer = String.format(menu, REPORTS, ORDERS, SOLUTION, text);
        return new SendMessage(chatId, answer);
    }

    @Override
    public String getLink() {
        return START;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;

@Component
public class VolunteerExecuteMenu implements ExecuteMessage {
    private final String menu = """
            Добро пожаловать новый волонтер!
            Меню волонтера:
            %s - показать заявки на усыновление
            %s - показать отчеты владельцев
            %s - принятие решения по владельцам
            """;

    private final VolunteerService volunteerService;

    public VolunteerExecuteMenu(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        Volunteer volunteer = new Volunteer();
        volunteer.setChatId(chatId);
        volunteer.setMessageMode(MessageMode.VOLUNTEER_DEFAULT);
        volunteerService.add(volunteer);
        String answer = String.format(menu, REPORTS, ORDERS, SOLUTION);
        return new SendMessage(chatId, answer);
    }

    @Override
    public String getLink() {
        return VOLUNTEER;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.START;
    }
}

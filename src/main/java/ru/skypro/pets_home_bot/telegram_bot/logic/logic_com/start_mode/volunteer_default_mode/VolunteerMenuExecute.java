package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.SOLUTION;
@Component
public class VolunteerMenuExecute implements ExecuteMessage {
    private final String menu = """
            Меню волонтера:
            %s - показать заявки на усыновление
            %s - показать отчеты владельцев
            %s - принятие решения по владельцам
            %s - ответить усыновителю
            """;
    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        String answer = String.format(menu, REPORTS, ORDERS, SOLUTION, ANSWER_PET_USER);
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

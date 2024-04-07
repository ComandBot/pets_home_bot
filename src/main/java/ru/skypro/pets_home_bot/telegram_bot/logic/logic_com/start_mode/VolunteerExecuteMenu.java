package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.VOLUNTEER;

@Component
public class VolunteerExecuteMenu implements ExecuteMessage {
    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        return new SendMessage(chatId, "menu");
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

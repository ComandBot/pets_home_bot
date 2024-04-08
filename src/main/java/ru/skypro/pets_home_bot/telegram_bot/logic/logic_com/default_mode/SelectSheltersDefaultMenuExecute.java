package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;
@Component
public class SelectSheltersDefaultMenuExecute implements ExecuteMessage {

    private final String menu = """
            Меню выбора приюта:
            %s - показать список приютов для котов
            %s - показать список приютов для собак
            """;
    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        return new SendMessage(chatId, String.format(menu, SHELTERS_CATS, SHELTERS_DOGS));
    }

    @Override
    public String getLink() {
        return START;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }
}

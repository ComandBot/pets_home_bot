package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;

@Component
public class SolutionOwnersExecute implements ExecuteMessage {
    private final String template = """
            Списки владельцев по категориям.
            %s - показать список владельцев у которых испытательный срок завершен
            %s - показать список владельцев не прошедших испытательный срок
            """;

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        return new SendMessage(chatId, String.format(template, OWNERS_END, OWNERS_CUR));
    }

    @Override
    public String getLink() {
        return SOLUTION;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

package ru.skypro.pets_home_bot.telegram_bot.logic.service.impl;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.service.SenderMessageService;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.Map;
import java.util.Set;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.DEFAULT_TEXT;

public abstract class SenderMessageServiceAbstract implements SenderMessageService {

    private final ParseUtil parseUtil;
    private  final Map<String, ExecuteMessage> messageMap;
    private final Set<MessageMode> messageModes = Set.of(
            MessageMode.HELP,
            MessageMode.CONTACT
    );
    public SenderMessageServiceAbstract(ParseUtil parseUtil, Map<String, ExecuteMessage> messageMap) {
        this.parseUtil = parseUtil;
        this.messageMap = messageMap;
    }

    @Override
    public BaseRequest answer(Update update) {
        validateUpdate(update);
        long chatId = update.message().chat().id();
        String text = parseUtil.parseLink(update.message().text());
        if (!messageMap.containsKey(text)) {
            if (messageModes.contains(getMessageMode())) {
                text = DEFAULT_TEXT;
            } else {
                return new SendMessage(chatId, "Команда не правильная");
            }
        }
        return messageMap.get(text).execute(update);
    }

    private void validateUpdate(Update update) {
        Message message = update.message();
        if (message == null) {
            throw new RuntimeException();
        }
    }

    @Override
    public abstract MessageMode getMessageMode();
}

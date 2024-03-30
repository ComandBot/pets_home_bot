package ru.skypro.pets_home_bot.telegram_bot.logic.service.impl;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.service.SenderMessageService;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SenderMessageServiceImpl implements SenderMessageService {

    private final ParseUtil parseUtil;
    private Map<String, ExecuteMessage> messageMap;

    public SenderMessageServiceImpl(ParseUtil parseUtil, List<ExecuteMessage> executeMessages) {
        this.parseUtil = parseUtil;
        this.messageMap = executeMessages.stream()
                .collect(Collectors.toMap(ExecuteMessage::getLink,
                Function.identity()));
    }

    @Override
    public BaseRequest answer(Update update) {
        validateUpdate(update);
        long chatId = update.message().chat().id();
        String text = parseUtil.parseLink(update.message().text());
        if (!messageMap.containsKey(text)) {
            return new SendMessage(chatId, "Команда не правильная");
        }
        return messageMap.get(text).execute(update);
    }

    private void validateUpdate(Update update) {
        Message message = update.message();
        if (message == null) {
            throw new RuntimeException();
        }
        String text = message.text();
        if (text == null) {
            throw new RuntimeException();
        }
    }
}

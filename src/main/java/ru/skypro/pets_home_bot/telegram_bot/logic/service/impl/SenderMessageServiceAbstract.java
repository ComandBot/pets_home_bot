package ru.skypro.pets_home_bot.telegram_bot.logic.service.impl;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.service.SenderMessageService;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.DEFAULT_TEXT;

public abstract class SenderMessageServiceAbstract implements SenderMessageService {

    private final ParseUtil parseUtil;
    private  final Map<String, ExecuteMessage> messageMap;
    private final Set<MessageMode> messageInputModes = Set.of(
            MessageMode.HELP,
            MessageMode.CONTACT,
            MessageMode.PHOTO_REPORT,
            MessageMode.DIET_REPORT,
            MessageMode.CONDITION_REPORT,
            MessageMode.BEHAVIOR_REPORT,
            MessageMode.COMMUNICATION_USER,
            MessageMode.COMMUNICATION_VOLUNTEER
    );

    public SenderMessageServiceAbstract(ParseUtil parseUtil, List<ExecuteMessage> executeMessages,
                                        MessageMode messageMode) {
        this.parseUtil = parseUtil;
        this.messageMap = executeMessages.stream()
                .filter(e -> e.getMessageMode().equals(messageMode))
                .collect(Collectors.toMap(ExecuteMessage::getLink,
                        Function.identity()));
    }

    @Override
    public BaseRequest answer(Update update) {
        validateUpdate(update);
        long chatId = update.message().chat().id();
        String text = update.message().text();
        if (text != null) {
            int length = text.split("_").length;
            if (length == 3) {
                text = parseUtil.parseTwoLink(text);
            }
            if (length == 2) {
                text = parseUtil.parseLink(text);
            }
        }
        if (!messageMap.containsKey(text)) {
            if (messageInputModes.contains(getMessageMode())) {
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

package ru.skypro.pets_home_bot.telegram_bot.logic.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class SenderMessageServiceStart extends SenderMessageServiceAbstract {
    public SenderMessageServiceStart(ParseUtil parseUtil, List<ExecuteMessage> executeMessages) {
        super(parseUtil, executeMessages.stream()
                .filter(e -> e.getMessageMode().equals(MessageMode.START))
                .collect(Collectors.toMap(ExecuteMessage::getLink,
                        Function.identity())));
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.START;
    }
}

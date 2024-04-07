package ru.skypro.pets_home_bot.telegram_bot.logic.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.List;
@Service
public class SendMessageServiceBehaviorReport extends SenderMessageServiceAbstract {
    public SendMessageServiceBehaviorReport(ParseUtil parseUtil, List<ExecuteMessage> executeMessages) {
        super(parseUtil, executeMessages, MessageMode.BEHAVIOR_REPORT);
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.BEHAVIOR_REPORT;
    }
}

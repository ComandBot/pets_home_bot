package ru.skypro.pets_home_bot.telegram_bot.logic.service.impl.pet_user_services;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.service.impl.SenderMessageServiceAbstract;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;
import java.util.List;

@Service
public class SendMessageServiceConditionSendReport extends SenderMessageServiceAbstract {
    public SendMessageServiceConditionSendReport(ParseUtil parseUtil, List<ExecuteMessage> executeMessages) {
        super(parseUtil, executeMessages, MessageMode.CONDITION_REPORT);
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.CONDITION_REPORT;
    }
}

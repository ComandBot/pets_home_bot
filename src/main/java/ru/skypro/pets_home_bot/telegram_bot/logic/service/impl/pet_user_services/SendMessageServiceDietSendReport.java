package ru.skypro.pets_home_bot.telegram_bot.logic.service.impl.pet_user_services;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.service.impl.SenderMessageServiceAbstract;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;
import java.util.List;

@Service
public class SendMessageServiceDietSendReport extends SenderMessageServiceAbstract {
    public SendMessageServiceDietSendReport(ParseUtil parseUtil, List<ExecuteMessage> executeMessages) {
        super(parseUtil, executeMessages, MessageMode.DIET_REPORT);
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DIET_REPORT;
    }
}

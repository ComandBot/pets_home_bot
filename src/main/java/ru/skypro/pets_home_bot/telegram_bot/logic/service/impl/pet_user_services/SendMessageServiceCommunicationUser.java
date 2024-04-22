package ru.skypro.pets_home_bot.telegram_bot.logic.service.impl.pet_user_services;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.service.impl.SenderMessageServiceAbstract;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;
import java.util.List;

@Service
public class SendMessageServiceCommunicationUser extends SenderMessageServiceAbstract {
    public SendMessageServiceCommunicationUser(ParseUtil parseUtil, List<ExecuteMessage> executeMessages) {
        super(parseUtil, executeMessages, MessageMode.COMMUNICATION_USER);
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.COMMUNICATION_USER;
    }
}

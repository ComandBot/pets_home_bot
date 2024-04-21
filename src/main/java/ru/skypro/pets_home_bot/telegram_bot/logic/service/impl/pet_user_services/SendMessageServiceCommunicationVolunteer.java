package ru.skypro.pets_home_bot.telegram_bot.logic.service.impl.pet_user_services;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.service.impl.SenderMessageServiceAbstract;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;
import java.util.List;

@Service
public class SendMessageServiceCommunicationVolunteer extends SenderMessageServiceAbstract {
    public SendMessageServiceCommunicationVolunteer(ParseUtil parseUtil, List<ExecuteMessage> executeMessages) {
        super(parseUtil, executeMessages, MessageMode.COMMUNICATION_VOLUNTEER);
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.COMMUNICATION_VOLUNTEER;
    }
}

package ru.skypro.pets_home_bot.telegram_bot.logic.service.impl.volunteer_services;

import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.service.impl.SenderMessageServiceAbstract;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;
import java.util.List;

@Service
public class SenderMessageServiceVolunteerDefault extends SenderMessageServiceAbstract {
    public SenderMessageServiceVolunteerDefault(ParseUtil parseUtil, List<ExecuteMessage> executeMessages) {
        super(parseUtil, executeMessages, MessageMode.VOLUNTEER_DEFAULT);
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

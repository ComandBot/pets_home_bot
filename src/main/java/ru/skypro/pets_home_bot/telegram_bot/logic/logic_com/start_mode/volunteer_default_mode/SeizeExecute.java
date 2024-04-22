package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.SEIZE_NUM_NUM;
@Component
public class SeizeExecute implements ExecuteMessage {
    private final AgreementExecute agreementExecute;

    public SeizeExecute(AgreementExecute agreementExecute) {
        this.agreementExecute = agreementExecute;
    }

    @Override
    public BaseRequest execute(Update update) {
        return agreementExecute.execute(update);
    }

    @Override
    public String getLink() {
        return SEIZE_NUM_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

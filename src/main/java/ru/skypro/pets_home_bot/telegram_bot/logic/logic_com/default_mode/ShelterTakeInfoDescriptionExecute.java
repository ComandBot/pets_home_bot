package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Consultation;
import ru.skypro.pets_home_bot.api_bot.service.ConsultationService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.SHELTER_TAKE_INFO_ID_NUM;
@Component
public class ShelterTakeInfoDescriptionExecute implements ExecuteMessage {
    private final ParseUtil parseUtil;
    private final ConsultationService consultationService;

    public ShelterTakeInfoDescriptionExecute(ParseUtil parseUtil, ConsultationService consultationService) {
        this.parseUtil = parseUtil;
        this.consultationService = consultationService;
    }

    @Override
    public BaseRequest execute(Update update) {
        String text = update.message().text();
        long chatId = update.message().chat().id();
        int idConsultation = parseUtil.getIdLink(text);
        Optional<Consultation> consultationOptional = consultationService.findConsultationById(idConsultation);
        if (consultationOptional.isEmpty()) {
            return new SendMessage(chatId, "Такой информации нет");
        }
        return new SendMessage(chatId, consultationOptional.get().getDefinition());
    }

    @Override
    public String getLink() {
        return SHELTER_TAKE_INFO_ID_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }
}

package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Consultation;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.service.ConsultationService;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.List;
import java.util.stream.Collectors;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;

@Component
public class ShelterTakeInfosExecute implements ExecuteMessage {
    private final String menu = """
            Правила усыновления животного из приюта %s
            %s - показать список животных
            %s - дать номер телефона
            %s - позвать волонтера
            """;
    private final ParseUtil parseUtil;
    private final ShelterService shelterService;
    private final ConsultationService consultationService;

    public ShelterTakeInfosExecute(ParseUtil parseUtil, ShelterService shelterService, ConsultationService consultationService) {
        this.parseUtil = parseUtil;
        this.shelterService = shelterService;
        this.consultationService = consultationService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        int idShelter = parseUtil.getIdLink(update.message().text());
        Shelter shelter = shelterService.findByShelterId(idShelter).get();
        List<Consultation> consultations = consultationService.findConsultationsByShelterId(idShelter);
        String consStringList = "Список информации:\n"
                + consultations.stream()
                .map(e -> parseUtil.tempParse(SHELTER_TAKE_INFO_ID_NUM, e.getId()) + " - " + e.getTypeConsultation())
                .collect(Collectors.joining("\n"));
        String result = String.format(menu + consStringList,
                shelter.getNameShelter(),
                parseUtil.tempParse(SHOW_LIST_PETS_SHELTER_NUM, idShelter),
                CONTACT,
                HELP);
        return new SendMessage(chatId, result);
    }

    @Override
    public String getLink() {
        return SHELTER_TAKE_INFOS_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }
}

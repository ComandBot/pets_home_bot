package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.PetsTypes;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.List;
import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;

@Component
public class ShelterMenuExecute implements ExecuteMessage {
    private final ShelterService shelterService;
    private final ParseUtil parseUtil;

    private final String menu = """
            Приют для %s:
            %s - Узнать информацию о приюте (этап 1)
            %s - Как взять животное из приюта (этап 2)
            %s - Прислать отчет о питомце (этап 3)
            %s - Позвать волонтера
            """;

    public ShelterMenuExecute(ShelterService shelterService, ParseUtil parseUtil) {
        this.shelterService = shelterService;
        this.parseUtil = parseUtil;
    }

    @Override
    public BaseRequest execute(Update update) {
        String link = update.message().text();
        int id = parseUtil.getIdLink(link);
        long chatId = update.message().chat().id();
        Optional<Shelter> shelterOptional = shelterService.findByShelterId(id);
        if (shelterOptional.isEmpty()) {
            return new SendMessage(chatId, "Такого приюта не существует");
        }
        Shelter shelter = shelterOptional.get();
        String typePets = shelter.getPetsTypes().equals(PetsTypes.CAT) ? "котов" : "собак";
        String shelterInfo = parseUtil.tempParse(SHELTER_INFOS_NUM, id);
        String shelterTakeInfo = parseUtil.tempParse(SHELTER_TAKE_INFOS_NUM, id);

        return new SendMessage(chatId,
                String.format(menu, typePets, shelterInfo, shelterTakeInfo, SEND_REPORT, HELP));
    }

    @Override
    public String getLink() {
        return SHELTER_NUM;
    }
}

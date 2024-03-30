package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import jdk.jfr.Category;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.model.ShelterInfo;
import ru.skypro.pets_home_bot.api_bot.service.ShelterInfoService;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.List;
import java.util.stream.Collectors;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;
@Component
public class ShelterInfoListExecuteMenu implements ExecuteMessage {

    private final String menu = """
            Инфрмация о приюте %s:
            %s - получить схему подъезда
            %s - дать номер телефона
            %s - позвать волонтера
            Списки доступной информации:
            """;

    private final ShelterInfoService shelterInfoService;
    private final ShelterService shelterService;
    private final ParseUtil parseUtil;

    public ShelterInfoListExecuteMenu(ShelterInfoService shelterInfoService, ShelterService shelterService, ParseUtil parseUtil) {
        this.shelterInfoService = shelterInfoService;
        this.shelterService = shelterService;
        this.parseUtil = parseUtil;
    }

    @Override
    public BaseRequest execute(Update update) {
        int id = parseUtil.getIdLink(update.message().text());
        Shelter shelter = shelterService.findByShelterId(id).get();
        long chatId = update.message().chat().id();
        List<ShelterInfo>  shelterInfos = shelterInfoService.findAllByShelterId(id);
        String result = menu + shelterInfos.stream()
                .map(e -> parseUtil.tempParse(SHELTER_INFO_ID_NUM, e.getId()) + " - " + e.getTypeInfo())
                .collect(Collectors.joining("\n"));
        return new SendMessage(chatId,
                String.format(result, shelter.getNameShelter(), SHELTER_SCHEME_NUM, CONTACT, HELP));
    }

    @Override
    public String getLink() {
        return SHELTER_INFOS_NUM;
    }
}

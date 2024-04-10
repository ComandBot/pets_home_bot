package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.List;
import java.util.stream.Collectors;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;
@Component
public class SheltersCatsMenu implements ExecuteMessage {
    private final String menu = "Список приютов для котов:\n";
    private final ShelterService shelterService;
    private final ParseUtil parseUtil;

    public SheltersCatsMenu(ShelterService shelterService, ParseUtil parseUtil) {
        this.shelterService = shelterService;
        this.parseUtil = parseUtil;
    }

    @Override
    public BaseRequest execute(Update update) {
        List<Shelter> shelters = shelterService.findByCatShelters();
        long chatId = update.message().chat().id();
        String result = menu
                + shelters.stream()
                .map(e -> parseUtil.tempParse(SHELTER_NUM, e.getId()) + " - " + e.getNameShelter())
                .collect(Collectors.joining("\n"));
        return new SendMessage(chatId, result);
    }

    @Override
    public String getLink() {
        return SHELTERS_CATS;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }
}

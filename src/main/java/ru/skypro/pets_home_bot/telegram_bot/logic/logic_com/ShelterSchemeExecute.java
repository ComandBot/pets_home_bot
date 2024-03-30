package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.model.ShelterScheme;
import ru.skypro.pets_home_bot.api_bot.service.ShelterSchemeService;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.SHELTER_SCHEME_NUM;
@Component
public class ShelterSchemeExecute implements ExecuteMessage {

    private final ShelterSchemeService shelterSchemeService;
    private final ParseUtil parseUtil;

    public ShelterSchemeExecute(ShelterSchemeService shelterSchemeService, ParseUtil parseUtil) {
        this.shelterSchemeService = shelterSchemeService;
        this.parseUtil = parseUtil;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        String link = update.message().text();
        int id = parseUtil.getIdLink(link);
        ShelterScheme shelterScheme = shelterSchemeService.findByShelterId(id);
        if (shelterScheme == null || shelterScheme.getScheme() == null) {
            return new SendMessage(chatId, "Схема не найдена");
        }
        return new SendPhoto(chatId, shelterScheme.getScheme());
    }

    @Override
    public String getLink() {
        return SHELTER_SCHEME_NUM;
    }
}

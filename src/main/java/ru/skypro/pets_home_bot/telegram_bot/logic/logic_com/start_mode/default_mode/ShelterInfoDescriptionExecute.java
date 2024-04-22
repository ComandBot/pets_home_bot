package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.ShelterInfo;
import ru.skypro.pets_home_bot.api_bot.service.ShelterInfoService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.SHELTER_INFO_ID_NUM;

@Component
public class ShelterInfoDescriptionExecute implements ExecuteMessage {

    private final ShelterInfoService shelterInfoService;
    private final ParseUtil parseUtil;

    public ShelterInfoDescriptionExecute(ShelterInfoService shelterInfoService, ParseUtil parseUtil) {
        this.shelterInfoService = shelterInfoService;
        this.parseUtil = parseUtil;
    }

    @Override
    public BaseRequest execute(Update update) {
        int id = parseUtil.getIdLink(update.message().text());
        long chatId = update.message().chat().id();
        Optional<ShelterInfo> shelterInfo = shelterInfoService.findById(id);
        if (shelterInfo.isEmpty()) {
            return new SendMessage(chatId, "Нет информации о приюте");
        }
        return new SendMessage(chatId, shelterInfo.get().getDefinition());
    }

    @Override
    public String getLink() {
        return SHELTER_INFO_ID_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }
}

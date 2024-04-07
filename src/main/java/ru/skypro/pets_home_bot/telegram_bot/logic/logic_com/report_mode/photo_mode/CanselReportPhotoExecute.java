package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.report_mode.photo_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.CANSEL_REPORT_PHOTO;
@Component
public class CanselReportPhotoExecute implements ExecuteMessage {
    private final PetUserService petUserService;

    public CanselReportPhotoExecute(PetUserService petUserService) {
        this.petUserService = petUserService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Такой пользователь не зарегестрирован");
        }
        petUser.setMessageMode(MessageMode.REPORT);
        petUserService.add(petUser);
        return new SendMessage(chatId, "Вы вышли из ражимаа отправки фото");
    }

    @Override
    public String getLink() {
        return CANSEL_REPORT_PHOTO;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.PHOTO_REPORT;
    }
}

package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode.report_mode.diet_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.CANSEL_REPORT_DIET;
@Component
public class CanselReportDietExecute implements ExecuteMessage {
    private final PetUserService petUserService;

    public CanselReportDietExecute(PetUserService petUserService) {
        this.petUserService = petUserService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Пользователь не зарегестрирован");
        }
        petUser.setMessageMode(MessageMode.REPORT);
        petUserService.add(petUser);
        return new SendMessage(chatId, "Вы вышли из режима отправки режиа питания");
    }

    @Override
    public String getLink() {
        return CANSEL_REPORT_DIET;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DIET_REPORT;
    }
}

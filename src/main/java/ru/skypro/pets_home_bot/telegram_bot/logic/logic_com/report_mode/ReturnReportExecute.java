package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.report_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import jdk.jfr.Category;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.CANSEL_REPORT;
@Component
public class ReturnReportExecute implements ExecuteMessage {

    private final PetUserService petUserService;

    public ReturnReportExecute(PetUserService petUserService) {
        this.petUserService = petUserService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Такого пользователя нет");
        }
        petUser.setMessageMode(MessageMode.DEFAULT);
        petUserService.add(petUser);
        return new SendMessage(chatId, "Вы вышли из режима отправки отчета");
    }

    @Override
    public String getLink() {
        return CANSEL_REPORT;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.REPORT;
    }
}

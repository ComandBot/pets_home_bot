package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.report_mode.diet_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.default_mode.PetReportExecuteMenu;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.DEFAULT_TEXT;
@Component
public class SendDietExecute implements ExecuteMessage {
    private final PetReportExecuteMenu petReportExecuteMenu;
    private final PetUserService petUserService;

    public SendDietExecute(PetReportExecuteMenu petReportExecuteMenu, PetUserService petUserService) {
        this.petReportExecuteMenu = petReportExecuteMenu;
        this.petUserService = petUserService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Такого пользователя нет");
        }
        String diet = update.message().text();
        if (diet.isEmpty()) {
            return new SendMessage(chatId, "Сообщение отсутствует");
        }
        Report report = petReportExecuteMenu.getReport();
        if (report == null) {
            return new SendMessage(chatId, "Выйдете из режима отправки отчета и зайдите еще раз");
        }
        report.setDiet(diet);
        petUser.setMessageMode(MessageMode.REPORT);
        petUserService.add(petUser);
        return new SendMessage(chatId, "Описание питния животного загружено");
    }

    @Override
    public String getLink() {
        return DEFAULT_TEXT;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DIET_REPORT;
    }
}

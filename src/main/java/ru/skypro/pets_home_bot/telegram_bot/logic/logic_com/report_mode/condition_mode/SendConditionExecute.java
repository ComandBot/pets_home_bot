package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.report_mode.condition_mode;

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
public class SendConditionExecute implements ExecuteMessage {
    private final PetUserService petUserService;
    private final PetReportExecuteMenu petReportExecuteMenu;

    public SendConditionExecute(PetUserService petUserService, PetReportExecuteMenu petReportExecuteMenu) {
        this.petUserService = petUserService;
        this.petReportExecuteMenu = petReportExecuteMenu;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Такого пользователя нет");
        }
        String condition = update.message().text();
        if (condition.isEmpty()) {
            return new SendMessage(chatId, "Сообщение отсутствует");
        }
        Report report = petReportExecuteMenu.getReport(chatId);
        if (report == null) {
            return new SendMessage(chatId, "Выйдете из режима отправки отчета и зайдите еще раз");
        }
        report.setCondition(condition);
        petUser.setMessageMode(MessageMode.REPORT);
        petUserService.add(petUser);
        return new SendMessage(chatId, "Описание общего состояния животного загружено");
    }

    @Override
    public String getLink() {
        return DEFAULT_TEXT;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.CONDITION_REPORT;
    }
}

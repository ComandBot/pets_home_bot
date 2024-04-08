package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.report_mode.behavior_mode;

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
public class SendBehaviorExecute implements ExecuteMessage {

    private final PetUserService petUserService;
    private final PetReportExecuteMenu petReportExecuteMenu;

    public SendBehaviorExecute(PetUserService petUserService, PetReportExecuteMenu petReportExecuteMenu) {
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
        String behavior = update.message().text();
        if (behavior.isEmpty()) {
            return new SendMessage(chatId, "Сообщение отсутствует");
        }
        Report report = petReportExecuteMenu.getReport(chatId);
        if (report == null) {
            return new SendMessage(chatId, "Выйдете из режима отправки отчета и зайдите еще раз");
        }
        report.setBehaviour(behavior);
        petUser.setMessageMode(MessageMode.REPORT);
        petUserService.add(petUser);
        return new SendMessage(chatId, "Описание изменения поведеия загружено");
    }

    @Override
    public String getLink() {
        return DEFAULT_TEXT;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.BEHAVIOR_REPORT;
    }
}

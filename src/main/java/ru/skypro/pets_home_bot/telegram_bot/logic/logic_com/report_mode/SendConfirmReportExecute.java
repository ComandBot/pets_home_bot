package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.report_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.api_bot.service.ReportService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.default_mode.PetReportExecuteMenu;

import java.time.LocalDateTime;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.REPORT_CONFIRM;
@Component
public class SendConfirmReportExecute implements ExecuteMessage {
    private final PetReportExecuteMenu petReportExecuteMenu;
    private final ReportService reportService;
    private final PetUserService petUserService;

    public SendConfirmReportExecute(PetReportExecuteMenu petReportExecuteMenu, ReportService reportService, PetUserService petUserService) {
        this.petReportExecuteMenu = petReportExecuteMenu;
        this.reportService = reportService;
        this.petUserService = petUserService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        Report report = petReportExecuteMenu.getReport(chatId);
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Зарегестрируйтесь в боте");
        }
        if (report == null) {
            return new SendMessage(chatId, "Выйдите из режима отправки отчета и войдите по новой");
        }
        if (report.getPhoto() == null || report.getPhoto().length == 0) {
            return new SendMessage(chatId, "Загрузите фото питомца");
        }
        if (report.getDiet() == null) {
            return new SendMessage(chatId, "Загрузите описание рациона питания");
        }
        if (report.getCondition() == null) {
            return new SendMessage(chatId, "Загрузите описание общего состояния животного");
        }
        if (report.getBehaviour() == null) {
            return new SendMessage(chatId, "Загрузите описание изменения поведения");
        }
        report.setDateReport(LocalDateTime.now());
        reportService.add(report);
        petUser.setMessageMode(MessageMode.DEFAULT);
        petUserService.add(petUser);
        petReportExecuteMenu.removeReport(chatId);
        return new SendMessage(chatId, "Отчет успешно загружен");
    }

    @Override
    public String getLink() {
        return REPORT_CONFIRM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.REPORT;
    }
}

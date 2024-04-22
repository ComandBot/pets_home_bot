package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode.report_mode.photo_mode;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode.PetReportExecuteMenu;

import java.io.IOException;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.DEFAULT_TEXT;

@Component
public class SendPhotoExecute implements ExecuteMessage {

    private final PetReportExecuteMenu petReportExecuteMenu;
    private final PetUserService petUserService;
    private final TelegramBot telegramBot;

    public SendPhotoExecute(PetReportExecuteMenu petReportExecuteMenu, PetUserService petUserService, TelegramBot telegramBot) {
        this.petReportExecuteMenu = petReportExecuteMenu;
        this.petUserService = petUserService;
        this.telegramBot = telegramBot;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Такого пользователя нет");
        }
        PhotoSize[] photos = update.message().photo();
        if (photos == null) {
            return new SendMessage(chatId, "Фото не загружено");
        }
        String fieldId = photos[photos.length - 1].fileId();
        GetFile getFile = new GetFile(fieldId);
        GetFileResponse getFileResponse = telegramBot.execute(getFile);
        byte[] data;
        try {
            data = telegramBot.getFileContent(getFileResponse.file());
        } catch (IOException e) {
            return new SendMessage(chatId, "Загрузить фото не получилось");
        }
        Report report = petReportExecuteMenu.getReport(chatId);
        if (report == null) {
            return new SendMessage(chatId, "Выйдете из режима отправки отчета и зайдите еще раз");
        }
        report.setPhoto(data);
        petUser.setMessageMode(MessageMode.REPORT);
        petUserService.add(petUser);
        return new SendMessage(chatId, "Фото успешно загружено");
    }

    @Override
    public String getLink() {
        return DEFAULT_TEXT;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.PHOTO_REPORT;
    }
}

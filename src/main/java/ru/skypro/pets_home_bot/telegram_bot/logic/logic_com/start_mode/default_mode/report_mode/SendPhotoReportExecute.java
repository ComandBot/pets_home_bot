package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode.report_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.CANSEL_REPORT_PHOTO;
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.PHOTO_REPORT_PET;
@Component
public class SendPhotoReportExecute implements ExecuteMessage {
    private final PetUserService petUserService;

    public SendPhotoReportExecute(PetUserService petUserService) {
        this.petUserService = petUserService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Пользователь не существует");
        }
        petUser.setMessageMode(MessageMode.PHOTO_REPORT);
        petUserService.add(petUser);
        String answer = """
                Отправьте фото животного.
                %s - выйти из режима отправки фото
                """;
        return new SendMessage(chatId, String.format(answer, CANSEL_REPORT_PHOTO));
    }

    @Override
    public String getLink() {
        return PHOTO_REPORT_PET;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.REPORT;
    }
}

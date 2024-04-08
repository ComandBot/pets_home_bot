package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.report_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;
@Component
public class SendDietReportExecute implements ExecuteMessage {

    private final PetUserService petUserService;

    public SendDietReportExecute(PetUserService petUserService) {
        this.petUserService = petUserService;
    }
    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Пользователь не существует");
        }
        petUser.setMessageMode(MessageMode.DIET_REPORT);
        petUserService.add(petUser);
        String answer = """
                Отправьте описание питания животного.
                %s - выйти из режима отправки питания
                """;
        return new SendMessage(chatId, String.format(answer, CANSEL_REPORT_DIET));
    }

    @Override
    public String getLink() {
        return DIET_REPORT_PET;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.REPORT;
    }
}

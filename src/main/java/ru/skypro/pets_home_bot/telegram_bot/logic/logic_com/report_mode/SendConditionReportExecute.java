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
public class SendConditionReportExecute implements ExecuteMessage {

    private final PetUserService petUserService;

    public SendConditionReportExecute(PetUserService petUserService) {
        this.petUserService = petUserService;
    }
    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Пользователь не существует");
        }
        petUser.setMessageMode(MessageMode.CONDITION_REPORT);
        petUserService.add(petUser);
        String answer = """
                Опишите состояние животного
                %s - выйти из режима описания состояния животного
                """;
        return new SendMessage(chatId, String.format(answer, CANSEL_REPORT_CONDITION));
    }

    @Override
    public String getLink() {
        return CONDITION_PET;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.REPORT;
    }
}

package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.contact_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.CANSEL_CONTACT;
@Component
public class CanselContactExecuteMessage implements ExecuteMessage {

    private final PetUserService petUserService;

    public CanselContactExecuteMessage(PetUserService petUserService) {
        this.petUserService = petUserService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Пользователь не найден");
        }
        petUser.setMessageMode(MessageMode.DEFAULT);
        petUserService.add(petUser);
        return new SendMessage(chatId, "Вы вышли из режима отправки контакта");
    }

    @Override
    public String getLink() {
        return CANSEL_CONTACT;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.CONTACT;
    }
}

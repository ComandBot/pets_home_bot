package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.CANSEL_CONTACT;
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.CONTACT;

@Component
public class ContactExecuteMenu implements ExecuteMessage {
    private final String menu = """
            Введите ваш номер телефона в формате +7-9**-***-**-**
            %s - выйти из режима ввода
            """;
    private final PetUserService petUserService;
    private final ParseUtil parseUtil;
    private final String regex = "^\\+\\d+\\-\\(\\d{3}\\)\\-\\d{3}\\-\\d{2}\\-\\d{2}$";
    public ContactExecuteMenu(PetUserService petUserService, ParseUtil parseUtil) {
        this.petUserService = petUserService;
        this.parseUtil = parseUtil;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            String text = "Такого пользователя нет.";
            return new SendMessage(chatId, text);
        }
        petUser.setMessageMode(MessageMode.CONTACT);
        petUserService.add(petUser);
        return new SendMessage(chatId, String.format(menu, CANSEL_CONTACT));
    }

    @Override
    public String getLink() {
        return CONTACT;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }
}

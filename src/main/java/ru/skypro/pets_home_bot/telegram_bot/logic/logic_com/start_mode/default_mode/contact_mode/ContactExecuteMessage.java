package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode.contact_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Contact;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.ContactService;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;

@Component
public class ContactExecuteMessage implements ExecuteMessage {
    private final PetUserService petUserService;
    private final ContactService contactService;
    private final ParseUtil parseUtil;

    public ContactExecuteMessage(PetUserService petUserService, ContactService contactService, ParseUtil parseUtil) {
        this.petUserService = petUserService;
        this.contactService = contactService;
        this.parseUtil = parseUtil;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Такого пользователя нет");
        }
        String phone = update.message().text();
        if (!parseUtil.isValidPhone(phone)) {
            String answer = "Введите телефон еще раз или нажмите %s";
            return new SendMessage(chatId, String.format(answer, CANSEL_CONTACT));
        }
        petUser.setMessageMode(MessageMode.DEFAULT);
        petUserService.add(petUser);
        Contact contact = new Contact();
        contact.setPhoneNumber(phone);
        contact.setPetUser(petUser);
        contactService.add(contact);
        return new SendMessage(chatId, "Номер телефона добавлен успешно");
    }

    @Override
    public String getLink() {
        return DEFAULT_TEXT;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.CONTACT;
    }
}

package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode.communication_user_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.CANSEL_COMMUNICATION;

@Component
public class ExitCommunicationPetUserExecute implements ExecuteMessage {
    private final PetUserService petUserService;
    private final VolunteerService volunteerService;

    public ExitCommunicationPetUserExecute(PetUserService petUserService, VolunteerService volunteerService) {
        this.petUserService = petUserService;
        this.volunteerService = volunteerService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Пользователь не зарегестрирован");
        }
        petUser.setMessageMode(MessageMode.DEFAULT);
        petUserService.add(petUser);
        Volunteer volunteer = volunteerService.findByWorkUserId(petUser);
        if (volunteer != null) {
            volunteer.setWorkUserId(null);
            volunteerService.add(volunteer);
        }
        return new SendMessage(chatId, "Вы вышли из режима общения с волонтером");
    }

    @Override
    public String getLink() {
        return CANSEL_COMMUNICATION;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.COMMUNICATION_USER;
    }
}

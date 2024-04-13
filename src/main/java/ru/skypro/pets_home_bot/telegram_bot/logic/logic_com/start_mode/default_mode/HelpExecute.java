package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode;

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
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.HELP;

@Component
public class HelpExecute implements ExecuteMessage {

    String menu = """
                Напишите сообщение волонтеру
                %s - нажмите для выхода из режима общения
                """;
    private final PetUserService petUserService;
    private final VolunteerService volunteerService;

    public HelpExecute(PetUserService petUserService, VolunteerService volunteerService) {
        this.petUserService = petUserService;
        this.volunteerService = volunteerService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();

        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Пользователь не найден");
        }

        Volunteer volunteer = volunteerService.findFirstByWorkUserIdIsNull();
        if (volunteer == null)  {
            volunteer = volunteerService.findByWorkUserId(petUser);
        }
        if (volunteer == null) {
            return new SendMessage(chatId, "Свободные волонтеры отсутствуют");
        }
        petUser.setMessageMode(MessageMode.COMMUNICATION_USER);
        petUserService.add(petUser);
        volunteer.setWorkUserId(petUser);
        volunteerService.add(volunteer);
        String answer = String.format(menu, CANSEL_COMMUNICATION);
        return new SendMessage(chatId, answer);
    }

    @Override
    public String getLink() {
        return HELP;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }
}

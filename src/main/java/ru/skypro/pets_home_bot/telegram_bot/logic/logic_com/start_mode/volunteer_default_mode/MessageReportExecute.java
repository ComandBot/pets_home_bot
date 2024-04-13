package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

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
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.CANSEL_COMMUNICATION;
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.MESSAGE_USER_ID_NUM;
@Component
public class MessageReportExecute implements ExecuteMessage {
    private final String template = """
            Напишите сообщение пользователю.
            %s - прекратить общение
            """;
    private final VolunteerService volunteerService;
    private final PetUserService petUserService;
    private final ParseUtil parseUtil;

    public MessageReportExecute(VolunteerService volunteerService, PetUserService petUserService, ParseUtil parseUtil) {
        this.volunteerService = volunteerService;
        this.petUserService = petUserService;
        this.parseUtil = parseUtil;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        String text = update.message().text();
        Volunteer volunteer = volunteerService.findByChatIdVolunteer(chatId);
        if(volunteer == null) {
            return new SendMessage(chatId, "Волонтер не зарегестрирован");
        }
        PetUser petUser = volunteer.getWorkUserId();
        if (petUser == null) {
            int idUser = parseUtil.getIdLink(text);
            Optional<PetUser> petUserOptional = petUserService.findById(idUser);
            if (petUserOptional.isEmpty()) {
                return new SendMessage(chatId, "Такой пользователь не зарегестрирован");
            }
            volunteer.setWorkUserId(petUserOptional.get());
        }
        volunteer.setMessageMode(MessageMode.COMMUNICATION_VOLUNTEER);
        volunteerService.add(volunteer);
        String answer = String.format(template, CANSEL_COMMUNICATION);
        return new SendMessage(chatId, answer);
    }

    @Override
    public String getLink() {
        return MESSAGE_USER_ID_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

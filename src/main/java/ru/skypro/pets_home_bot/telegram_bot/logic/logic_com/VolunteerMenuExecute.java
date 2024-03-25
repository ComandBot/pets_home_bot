package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.enums.TypeSender;
@Component
public class VolunteerMenuExecute implements ExecuteMessage {
    private final VolunteerService volunteerService;
    private final ExecuteMessage executeMessage;

    public VolunteerMenuExecute(VolunteerService volunteerService,
                                @Qualifier("authorizationExecute") ExecuteMessage executeMessage) {
        this.volunteerService = volunteerService;
        this.executeMessage = executeMessage;
    }

    @Override
    public String execute(String link) {
        long chatId = Long.parseLong(link);
        if (volunteerService.findByChatIdVolunteer(chatId) != null) {
            return "menu";
        }
        return executeMessage.execute(link);
    }

    @Override
    public TypeSender getTypeSender() {
        return TypeSender.NO_BEAN;
    }
}

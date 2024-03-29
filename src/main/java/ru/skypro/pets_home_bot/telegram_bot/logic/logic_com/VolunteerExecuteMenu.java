package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.VOLUNTEER;

@Component
public class VolunteerExecuteMenu implements ExecuteMessage {
    @Override
    public String execute(Update update) {
        return null;
    }

    @Override
    public String getLink() {
        return VOLUNTEER;
    }
}

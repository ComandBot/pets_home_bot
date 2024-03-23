package ru.skypro.pets_home_bot.telegram_bot.logic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.telegram_bot.enums.TypeSender;
@Component
@Slf4j
public class ShelterListDogsSelectExecute implements ExecuteMessage {

    @Override
    public String execute(String link) {
        return "Выводим список приютов для собак";
    }

    @Override
    public TypeSender getTypeSender() {
        return TypeSender.SHELTER_SELECT_DOGS;
    }
}

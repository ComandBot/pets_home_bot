package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;

import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.telegram_bot.logic.constants.TemplateAnswer;
import ru.skypro.pets_home_bot.telegram_bot.logic.enums.TypeSender;
@Component
public class AuthorizationExecute implements ExecuteMessage {
    @Override
    public String execute(String link) {
        return String.format(TemplateAnswer.AUTHORIZATION_MENU,
                TypeSender.PET_USER, TypeSender.VOLUNTEER);
    }

    @Override
    public TypeSender getTypeSender() {
        return TypeSender.NO_BEAN;
    }
}

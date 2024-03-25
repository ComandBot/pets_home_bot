package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.enums.TypeSender;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.TemplateAnswer.PET_USER_SELECT_TYPE_SHELTER;

@Component
public class PetUserMenuExecute implements ExecuteMessage {

    private final PetUserService petUserService;
    private final ExecuteMessage executeMessage;

    public PetUserMenuExecute(PetUserService petUserService,
                              @Qualifier("volunteerMenuExecute") ExecuteMessage executeMessage) {
        this.petUserService = petUserService;
        this.executeMessage = executeMessage;
    }

    @Override
    public String execute(String link) {
        long chatId = Long.parseLong(link);
        if (petUserService.findByChatIdPetUser(chatId) != null) {
            return String.format(PET_USER_SELECT_TYPE_SHELTER,
                    TypeSender.SHELTER_SELECT_CATS,
                    TypeSender.SHELTER_SELECT_DOGS);
        }
        return executeMessage.execute(link);
    }

    @Override
    public TypeSender getTypeSender() {
        return TypeSender.START;
    }
}

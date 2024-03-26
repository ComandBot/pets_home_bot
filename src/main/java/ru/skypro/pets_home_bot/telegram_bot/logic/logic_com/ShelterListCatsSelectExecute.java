package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;

import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;
import ru.skypro.pets_home_bot.telegram_bot.logic.constants.TemplateAnswer;
import ru.skypro.pets_home_bot.telegram_bot.logic.enums.TypeSender;

import java.util.List;

@Component
public class ShelterListCatsSelectExecute implements ExecuteMessage {

    private final ShelterService shelterService;

    public ShelterListCatsSelectExecute(ShelterService shelterService) {
        this.shelterService = shelterService;
    }


    @Override
    public String execute(String link) {
        List<Shelter> shelters = shelterService.findByCatShelters();
        StringBuilder builder = new StringBuilder(TemplateAnswer.SHELTERS_LIST_CATS);
        shelters.forEach(e -> builder.append(e.getLink()).append("\n"));
        return builder.toString();
    }

    @Override
    public TypeSender getTypeSender() {
        return TypeSender.SHELTER_SELECT_CATS;
    }
}

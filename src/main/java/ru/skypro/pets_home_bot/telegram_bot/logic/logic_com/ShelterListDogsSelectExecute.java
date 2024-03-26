package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;
import ru.skypro.pets_home_bot.telegram_bot.logic.constants.TemplateAnswer;
import ru.skypro.pets_home_bot.telegram_bot.logic.enums.TypeSender;

import java.util.List;

@Component
@Slf4j
public class ShelterListDogsSelectExecute implements ExecuteMessage {

    private final ShelterService shelterService;

    public ShelterListDogsSelectExecute(ShelterService shelterService) {
        this.shelterService = shelterService;
    }


    @Override
    public String execute(String link) {
        List<Shelter> shelters = shelterService.findByDogShelters();
        StringBuilder builder = new StringBuilder(TemplateAnswer.SHELTERS_LIST_DOGS);
        shelters.forEach(e -> builder.append(e.getLink()).append("\n"));
        return builder.toString();
    }

    @Override
    public TypeSender getTypeSender() {
        return TypeSender.SHELTER_SELECT_DOGS;
    }
}

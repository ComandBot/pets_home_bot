package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;

import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;
import ru.skypro.pets_home_bot.telegram_bot.enums.TypeSender;

public class SelectedShelterMessageExecute implements ExecuteMessage {
    private final ShelterService shelterService;

    public SelectedShelterMessageExecute(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @Override
    public String execute(String link) {
        Shelter shelter = shelterService.findByShelterLink(link);
        return "Выводим общую информацию о приюте";
    }

    @Override
    public TypeSender getTypeSender() {
        return TypeSender.SHELTER;
    }
}

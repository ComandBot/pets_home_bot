package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;

import ru.skypro.pets_home_bot.telegram_bot.enums.TypeSender;

public class ShelterListCatsSelectExecute implements ExecuteMessage{
    @Override
    public String execute(String link) {
        return "Выводим список приютов для котов";
    }

    @Override
    public TypeSender getTypeSender() {
        return TypeSender.SHELTER_SELECT_CATS;
    }
}

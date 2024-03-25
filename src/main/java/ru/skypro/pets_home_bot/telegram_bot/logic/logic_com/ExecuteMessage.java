package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;

import ru.skypro.pets_home_bot.telegram_bot.logic.enums.TypeSender;


public interface ExecuteMessage {
    String execute(String link);
    TypeSender getTypeSender();

}

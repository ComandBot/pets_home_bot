package ru.skypro.pets_home_bot.telegram_bot.logic;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import ru.skypro.pets_home_bot.telegram_bot.enums.TypeSender;


public interface ExecuteMessage {
    String execute(String link);
    TypeSender getTypeSender();

}

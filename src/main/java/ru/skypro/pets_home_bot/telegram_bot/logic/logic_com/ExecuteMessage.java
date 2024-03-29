package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;


import com.pengrad.telegrambot.model.Update;

public interface ExecuteMessage {
    String execute(Update update);
    String getLink();

}

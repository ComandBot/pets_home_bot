package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;


import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;

public interface ExecuteMessage {
    BaseRequest execute(Update update);
    String getLink();

}

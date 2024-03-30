package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;


import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;

public interface ExecuteMessage {
    BaseRequest execute(Update update);
    String getLink();

    MessageMode getMessageMode();

}

package ru.skypro.pets_home_bot.telegram_bot.logic.service;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;

public interface SenderMessageService {
    BaseRequest answer(Update update);

    MessageMode getMessageMode();
}

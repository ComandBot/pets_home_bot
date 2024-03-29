package ru.skypro.pets_home_bot.telegram_bot.logic.service;

import com.pengrad.telegrambot.model.Update;

public interface SenderMessageService {
    String answer(Update update);
}

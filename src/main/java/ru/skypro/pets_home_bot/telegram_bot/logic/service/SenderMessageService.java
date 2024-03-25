package ru.skypro.pets_home_bot.telegram_bot.logic.service;

public interface SenderMessageService {
    String answer(String link);
    String answer(String link, String chatId);
}

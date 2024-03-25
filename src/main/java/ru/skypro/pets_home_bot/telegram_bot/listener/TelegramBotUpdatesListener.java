package ru.skypro.pets_home_bot.telegram_bot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    @Autowired
    private TelegramBot telegramBot;
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            Message message = update.message();
            if (message == null) {
                logger.error("Received unsupported message type" + update);
                return;
            }
            long chatId = message.chat().id();
            String taskMessage = message.text();
            if ("/info".equals(taskMessage)) {
                String messageTmp = "Введите задачу в формате <01.01.2022 20:00 Сделать домашнюю работу>";
                SendMessage sendMessage = new SendMessage(chatId, messageTmp);
                telegramBot.execute(sendMessage);
                return;
            }
            if ("/start".equals(taskMessage)) {
                String messageTmp = "Для работы с ботом введите /info";
                SendMessage sendMessage = new SendMessage(chatId, messageTmp);
                telegramBot.execute(sendMessage);
                return;
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

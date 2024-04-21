package ru.skypro.pets_home_bot.telegram_bot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.telegram_bot.logic.service.ManagerMessageService;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;

    private final ManagerMessageService managerService;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, ManagerMessageService managerService) {
        this.telegramBot = telegramBot;
        this.managerService = managerService;
    }


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            BaseRequest answer = managerService.answer(update);
            telegramBot.execute(answer);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

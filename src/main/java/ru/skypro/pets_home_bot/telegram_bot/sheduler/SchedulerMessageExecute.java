package ru.skypro.pets_home_bot.telegram_bot.sheduler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.service.OwnerService;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;

import java.util.List;

@Component
public class SchedulerMessageExecute {
    private final TelegramBot telegramBot;
    private final OwnerService ownerService;
    private final VolunteerService volunteerService;

    public SchedulerMessageExecute(TelegramBot telegramBot, OwnerService ownerService, VolunteerService volunteerService) {
        this.telegramBot = telegramBot;
        this.ownerService = ownerService;
        this.volunteerService = volunteerService;
    }

    @Scheduled(cron = "0 0/59 * * * *")
    public void run() {
        List<Owner> owners = ownerService.getOwnersAfterTwoDaysReport();
        if (owners == null || owners.isEmpty()) {
            return;
        }
        String template = "На питомца по имени %s нужно прислать отчет";
        owners.forEach(e -> telegramBot.execute(
                new SendMessage(e.getOwnerId().getPetUser().getChatId(),
                        String.format(template, e.getOwnerId().getPet().getName()))
        ));
    }

    @Scheduled(cron = "0 0 21 * * *")
    public void volunteerRun() {
        List<Volunteer> volunteers = volunteerService.getAll();
        volunteers.forEach( e ->
                telegramBot.execute(new SendMessage(e.getChatId() , "Время смотреть отчеты"))
        );

    }
}

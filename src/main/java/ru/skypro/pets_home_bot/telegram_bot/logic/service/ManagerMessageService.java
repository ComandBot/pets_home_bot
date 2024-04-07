package ru.skypro.pets_home_bot.telegram_bot.logic.service;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class ManagerMessageService {
    private Map<MessageMode, SenderMessageService> messageServiceMap;
    private final PetUserService petUserService;
    private final VolunteerService volunteerService;

    public ManagerMessageService(List<SenderMessageService> senderMessageServices, PetUserService petUserService, VolunteerService volunteerService) {
        this.messageServiceMap = senderMessageServices.stream()
                .collect(Collectors
                        .toMap(SenderMessageService::getMessageMode, Function.identity()));
        this.petUserService = petUserService;
        this.volunteerService = volunteerService;
    }

    public BaseRequest answer(Update update) {
        long chatId = update.message().chat().id();
        return messageServiceMap.get(getMessageMode(chatId)).answer(update);
    }

    private MessageMode getMessageMode(long chatId) {
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser != null && petUser.getMessageMode() != null) {
            return petUser.getMessageMode();
        }
        Volunteer volunteer = volunteerService.findByChatIdVolunteer(chatId);
        if (volunteer != null && volunteer.getMessageMode() != null) {
            return volunteer.getMessageMode();
        }
        return MessageMode.START;
    }
}

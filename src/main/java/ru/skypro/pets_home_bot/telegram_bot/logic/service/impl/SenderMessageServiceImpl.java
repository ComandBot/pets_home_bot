package ru.skypro.pets_home_bot.telegram_bot.logic.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.enums.TypeSender;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.service.SenderMessageService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SenderMessageServiceImpl implements SenderMessageService {
    private final PetUserService petUserService;
    private final VolunteerService volunteerService;

    private Map<String, ExecuteMessage> messageMap;

    public SenderMessageServiceImpl(PetUserService petUserService, VolunteerService volunteerService,
                                    List<ExecuteMessage> executeMessages) {
        this.petUserService = petUserService;
        this.volunteerService = volunteerService;
        this.messageMap = executeMessages.stream().collect(Collectors.toMap(e -> e.getTypeSender().getLink(),
                Function.identity()));
    }

    @Override
    public String answer(String link, long chatId) {
        if (TypeSender.START.getLink().equals(link)) {
            return startAnswer(chatId);
        }
        if (messageMap.containsKey(link)) {
            return messageMap.get(link).execute(link);
        }
        return null;
    }

    private String startAnswer(long chatId) {
        if (petUserService.findByChatIdPetUser(chatId) != null) {
            return  "Выводим меню выбора приютов";
        }
        if (volunteerService.findByChatIdVolunteer(chatId) != null) {
            return "Выводим  меню для волонтера";
        }
        return "Выводим меню авторизации и приветствуем";
    }
}

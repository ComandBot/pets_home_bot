package ru.skypro.pets_home_bot.telegram_bot.logic.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.enums.TypeSender;
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
        this.messageMap = executeMessages.stream()
                .filter(e -> !e.getTypeSender().equals(TypeSender.NO_BEAN))
                .collect(Collectors.toMap(e -> e.getTypeSender().getLink(),
                Function.identity()));
    }

    @Override
    public String answer(String link) {
        return messageMap.get(parseLink(link)).execute(link);
    }

    @Override
    public String answer(String link, String chatId) {
        return messageMap.get(link).execute(chatId);
    }

    private String parseLink(String link) {
        return link.replaceAll("//d+", "n");
    }
}

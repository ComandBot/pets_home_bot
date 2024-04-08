package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.*;
import ru.skypro.pets_home_bot.api_bot.service.OwnerService;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.api_bot.service.ReportService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;

@Component
public class PetReportExecuteMenu implements ExecuteMessage {

    private final Map<Long, Report> reportMap;
    private final String menu = """
            Форма отправки отчета.
            %s - загрузить фото животного
            %s - описать рацион питания
            %s - описать общее самочуствие и привыкание к новому месту
            %s - описать изменения в поведении
            %s - отправить отчет  %s - выйти из режима отправки отчета 
            """;
    private final OwnerService ownerService;
    private final PetUserService petUserService;
    private final ParseUtil parseUtil;

    public PetReportExecuteMenu(OwnerService ownerService,
                                PetUserService petUserService,
                                ParseUtil parseUtil) {
        this.ownerService = ownerService;
        this.petUserService = petUserService;
        this.parseUtil = parseUtil;
        this.reportMap = new HashMap<>();
    }


    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Такой пользователь не зарегестрирова");
        }
        String link = update.message().text();
        int petId = parseUtil.getIdLink(link);
        Optional<Owner> ownerOptional = ownerService.findByPetIdAndPetUserId(petUser.getId(), petId);
        if (ownerOptional.isEmpty()) {
            return new SendMessage(chatId, "Владелец животного не обнаружен");
        }
        Report report = new Report();
        report.setOwner(ownerOptional.get());
        report.setViewed(false);
        reportMap.put(chatId, report);
        petUser.setMessageMode(MessageMode.REPORT);
        petUserService.add(petUser);
        String result = String.format(menu, PHOTO_REPORT_PET,
                DIET_REPORT_PET,
                CONDITION_PET,
                BEHAVIOR_PET,
                REPORT_CONFIRM,
                CANSEL_REPORT);

        return new SendMessage(chatId, result);
    }

    @Override
    public String getLink() {
        return REPORT_PET_ID_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }

    public Report getReport(long chatId) {
        return reportMap.getOrDefault(chatId, null);
    }

    public void removeReport(long chatId) {
        reportMap.remove(chatId);
    }
}

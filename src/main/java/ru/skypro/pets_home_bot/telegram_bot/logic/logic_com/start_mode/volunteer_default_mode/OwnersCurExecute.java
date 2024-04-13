package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.service.OwnerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;
@Component
public class OwnersCurExecute implements ExecuteMessage {
    private final String template = """
            Список не прошедших испытательный срок:
            Ссылка на владельца | Дата окончания срока
            ------------------------------------------
            %s
            """;
    private final OwnerService ownerService;
    private final ParseUtil parseUtil;

    public OwnersCurExecute(OwnerService ownerService, ParseUtil parseUtil) {
        this.ownerService = ownerService;
        this.parseUtil = parseUtil;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        List<Owner> owners = ownerService.findAllByDateDeliveryBetweenBeginAndEndTestPeriod();
        if (owners == null || owners.isEmpty()) {
            return new SendMessage(chatId, "Усыновителей не прошедших испытательный срок нет");
        }
        String tempRaw = "%s - %s";
        String strOwners = owners.stream()
                .map(e -> String.format(tempRaw,
                        parseUtil.tempParseTwoNum(OWNER_END_USER_PET_NUM_NUM,
                                e.getOwnerId().getPetUser().getId(),
                                e.getOwnerId().getPet().getId()),
                        e.getDateDelivery().plusDays(e.getTestPeriod()).format(DateTimeFormatter.ISO_DATE)))
                .collect(Collectors.joining("\n"));
        return new SendMessage(chatId,String.format(template, strOwners));
    }

    @Override
    public String getLink() {
        return OWNERS_CUR;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

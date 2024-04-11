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

import java.util.List;
import java.util.stream.Collectors;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.ORDERS;
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.OWNER_USER_ID_PET_ID_NUM1_NUM2;

@Component
public class OrdersListExecute implements ExecuteMessage {
    private final String template = """
            Список взаявок от усыновителей:
            %s
            """;
    private final OwnerService ownerService;
    private final ParseUtil parseUtil;

    public OrdersListExecute(OwnerService ownerService, ParseUtil parseUtil) {
        this.ownerService = ownerService;
        this.parseUtil = parseUtil;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        List<Owner> ownerList = ownerService.findAllByDateDeliveryIsNull();
        if (ownerList == null || ownerList.isEmpty()) {
            return new SendMessage(chatId, "Новых заявок на усыновление не поступало");
        }
        String text = ownerList.stream()
                .map(e -> parseUtil.tempParseTwoNum(OWNER_USER_ID_PET_ID_NUM1_NUM2,
                        e.getOwnerId().getPetUser().getId(),
                        e.getOwnerId().getPet().getId()))
                .collect(Collectors.joining("\n"));
        String answer = String.format(template, text);
        return new SendMessage(chatId, answer);
    }

    @Override
    public String getLink() {
        return ORDERS;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

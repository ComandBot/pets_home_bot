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
import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.INCREASE_FOURTEEN_NUM_NUM;
@Component
public class IncreaseFourteenExecute implements ExecuteMessage {
    private final ParseUtil parseUtil;
    private final OwnerService ownerService;
    private final int additionalPeriod = 14;

    public IncreaseFourteenExecute(ParseUtil parseUtil, OwnerService ownerService) {
        this.parseUtil = parseUtil;
        this.ownerService = ownerService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        String text = update.message().text();
        int[] userIdPetId = parseUtil.gePetUserIdAndPetId(text);
        Optional<Owner> ownerOptional =
                ownerService.findByPetUserIdAndPetId(userIdPetId[0], userIdPetId[1]);
        if (ownerOptional.isEmpty()) {
            return new SendMessage(chatId, "Владелец не найден");
        }
        Owner owner = ownerOptional.get();
        owner.setTestPeriod(owner.getTestPeriod() + additionalPeriod);
        ownerService.save(owner);
        return new SendMessage(chatId, String.format("Тестовый период увеличен на %d дней", additionalPeriod));
    }

    @Override
    public String getLink() {
        return INCREASE_FOURTEEN_NUM_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.OwnerService;
import ru.skypro.pets_home_bot.api_bot.service.PetService;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.HELP;
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.NO_ADOPTION_USER_PET_NUM1_NUM2;
@Component
public class AdoptionNoExecute implements ExecuteMessage {

    private final String template = """
            Вам отказано в усыновление питомца по имени %s
            Нажмите %s для связи с волонтером и ожидайте ответа
            """;

    private final ParseUtil parseUtil;
    private final PetUserService petUserService;
    private final OwnerService ownerService;
    private final PetService petService;

    public AdoptionNoExecute(ParseUtil parseUtil, PetUserService petUserService, OwnerService ownerService, PetService petService) {
        this.parseUtil = parseUtil;
        this.petUserService = petUserService;
        this.ownerService = ownerService;
        this.petService = petService;
    }

    @Override
    public BaseRequest execute(Update update) {
        String text = update.message().text();
        long chatId = update.message().chat().id();
        int[] args = parseUtil.gePetUserIdAndPetId(text);
        Optional<PetUser> petUserOptional = petUserService.findById(args[0]);
        if (petUserOptional.isEmpty()) {
            return new SendMessage(chatId, "Такой пользователь не зарегестрирован");
        }
        Optional<Pet> petOptional = petService.findById(args[1]);
        if (petOptional.isEmpty()) {
            return new SendMessage(chatId, "Животное не найдено");
        }
        Pet pet = petOptional.get();
        long petUserChatId = petUserOptional.get().getChatId();
        Optional<Owner> ownerOptional = ownerService.findByPetIdAndPetUserIdWhereDateNull(args[0], args[1]);
        if (ownerOptional.isEmpty()) {
            return new SendMessage(chatId, "Заявитель не найден или заявка обработана.");
        }
        Owner owner = ownerOptional.get();
        ownerService.deleteByOwnerId(owner.getOwnerId());
        String answer = String.format(template, pet.getName(), HELP);
        return new SendMessage(petUserChatId, answer);
    }

    @Override
    public String getLink() {
        return NO_ADOPTION_USER_PET_NUM1_NUM2;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

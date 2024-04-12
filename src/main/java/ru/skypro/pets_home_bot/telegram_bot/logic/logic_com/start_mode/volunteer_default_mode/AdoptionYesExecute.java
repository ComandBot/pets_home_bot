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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.YES_ADOPTION_USER_PET_NUM1_NUM2;

@Component
public class AdoptionYesExecute implements ExecuteMessage {
    String template = """
            Вы взяли питомца по имени %s на испытателный срок.
            Ваш испытательный срок составляет %d дней
            Не позже двух дней отправляйте отчеты.
            """;
    private final PetUserService petUserService;
    private final int days = 30;
    private final OwnerService ownerService;
    private final PetService petService;
    private final ParseUtil parseUtil;

    public AdoptionYesExecute(PetUserService petUserService, OwnerService ownerService, PetService petService, ParseUtil parseUtil) {
        this.petUserService = petUserService;
        this.ownerService = ownerService;
        this.petService = petService;
        this.parseUtil = parseUtil;
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
        LocalDateTime endOfDay = LocalDate.now().atStartOfDay();
        Owner owner = ownerOptional.get();
        owner.setTestPeriod(days);
        owner.setDateDelivery(endOfDay);
        ownerService.save(owner);
        String answer = String.format(template, pet.getName(), days);
        return new SendMessage(petUserChatId, answer);
    }

    @Override
    public String getLink() {
        return YES_ADOPTION_USER_PET_NUM1_NUM2;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

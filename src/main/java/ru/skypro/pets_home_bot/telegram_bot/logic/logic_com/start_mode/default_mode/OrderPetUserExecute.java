package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.OwnerId;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.OwnerService;
import ru.skypro.pets_home_bot.api_bot.service.PetService;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.PET_TAKE_ID_NUM;
@Component
public class OrderPetUserExecute implements ExecuteMessage {

    private final ParseUtil parseUtil;
    private final OwnerService ownerService;
    private final PetService petService;
    private final PetUserService petUserService;

    public OrderPetUserExecute(ParseUtil parseUtil, OwnerService ownerService, PetService petService, PetUserService petUserService) {
        this.parseUtil = parseUtil;
        this.ownerService = ownerService;
        this.petService = petService;
        this.petUserService = petUserService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Такого пользователя нет");
        }
        String text = update.message().text();
        int id = parseUtil.getIdLink(text);
        Optional<Pet> optionalPet = petService.findById(id);
        if (optionalPet.isEmpty()) {
            return new SendMessage(chatId, "Такого животного уже нет");
        }
        Pet pet = optionalPet.get();
        OwnerId ownerId = new OwnerId();
        ownerId.setPet(pet);
        ownerId.setPetUser(petUser);
        if (ownerService.findByOwnerId(ownerId).isPresent()) {
            return new SendMessage(chatId, "Вы уже подавали заявку на это животное");
        }
        Owner owner = new Owner();
        owner.setOwnerId(ownerId);
        ownerService.addOwner(owner);
        return new SendMessage(chatId, "Заявка подана, ждите ответа волонтера");
    }

    @Override
    public String getLink() {
        return PET_TAKE_ID_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }
}

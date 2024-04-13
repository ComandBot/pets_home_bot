package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.*;
import ru.skypro.pets_home_bot.api_bot.service.*;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;

@Component
public class ShowOrderExecute implements ExecuteMessage {

    private final String template = """
            Контактная информация заявителя:
            %s
            -------------------------------
            Описание животного:
            %s
            ------------------------------
            %s - разрешить усыновление 
            %s - отказать
            """;
    private final ParseUtil parseUtil;
    private final PetUserService petUserService;
    private final OwnerService ownerService;
    private final PetService petService;
    private final AvatarPetService avatarPetService;
    private final ContactService contactService;

    public ShowOrderExecute(ParseUtil parseUtil,
                            PetUserService petUserService,
                            OwnerService ownerService,
                            PetService petService, AvatarPetService avatarPetService,
                            ContactService contactService) {
        this.parseUtil = parseUtil;
        this.petUserService = petUserService;
        this.ownerService = ownerService;
        this.petService = petService;
        this.avatarPetService = avatarPetService;
        this.contactService = contactService;
    }

    @Override
    public BaseRequest execute(Update update) {
        String text = update.message().text();
        long chatId = update.message().chat().id();
        int[] args = parseUtil.gePetUserIdAndPetId(text);
        Optional<PetUser> optionalPetUser = petUserService.findById(args[0]);
        if (optionalPetUser.isEmpty()) {
            return new SendMessage(chatId, "Пользователь не зарегестрирован");
        }
        PetUser petUser = optionalPetUser.get();
        Optional<Pet> optionalPet = petService.findById(args[1]);
        if (optionalPet.isEmpty()) {
            return new SendMessage(chatId, "Животное не найдено");
        }
        Optional<Owner> ownerOptional = ownerService.findByPetIdAndPetUserIdWhereDateNull(args[0], args[1]);
        if (ownerOptional.isEmpty()) {
            return new SendMessage(chatId, "Заявка удалена");
        }
        List<Contact> contacts = contactService.findAllByPetUser(petUser);
        String contactInfo = "Контактная информация отсутствует";
        if (contacts != null) {
            contactInfo = contacts.stream()
                    .map(Contact::getPhoneNumber)
                    .collect(Collectors.joining("\n"));
        }
        Optional<AvatarPet> avatarPetOptional = avatarPetService.findAvatarPetByPetId(args[1]);
        String petInfo = "Описание животного отсутствует";
        if (avatarPetOptional.isEmpty() || avatarPetOptional.get().getData() == null) {
            return new SendMessage(chatId, String.format(template,
                    contactInfo,
                    petInfo,
                    parseUtil.tempParseTwoNum(YES_ADOPTION_USER_PET_NUM1_NUM2, args[0], args[1]),
                    parseUtil.tempParseTwoNum(NO_ADOPTION_USER_PET_NUM1_NUM2, args[0], args[1])));
        }
        AvatarPet avatarPet = avatarPetOptional.get();
        petInfo = avatarPet.getDescription();
        String answer = String.format(template, contactInfo, petInfo,
                parseUtil.tempParseTwoNum(YES_ADOPTION_USER_PET_NUM1_NUM2, args[0], args[1]),
                parseUtil.tempParseTwoNum(NO_ADOPTION_USER_PET_NUM1_NUM2, args[0], args[1]));
        return new SendPhoto(chatId, avatarPet.getData()).caption(answer);
    }

    @Override
    public String getLink() {
        return OWNER_USER_ID_PET_ID_NUM1_NUM2;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

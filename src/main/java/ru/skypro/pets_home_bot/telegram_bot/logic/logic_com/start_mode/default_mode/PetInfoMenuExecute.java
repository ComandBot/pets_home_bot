package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.AvatarPet;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.AvatarPetService;
import ru.skypro.pets_home_bot.api_bot.service.PetService;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;
@Component
public class PetInfoMenuExecute implements ExecuteMessage {

    private final String menu = """ 
            Питомец по имени %s:
            %s
            Описание:
            %s
            %s - Подать заявку на усыновление
            """;

    private final ParseUtil parseUtil;
    private final PetService petService;
    private final PetUserService petUserService;
    private final AvatarPetService avatarPetService;

    public PetInfoMenuExecute(ParseUtil parseUtil, PetService petService, PetUserService petUserService, AvatarPetService avatarPetService) {
        this.parseUtil = parseUtil;
        this.petService = petService;
        this.petUserService = petUserService;
        this.avatarPetService = avatarPetService;
    }

    @Override
    public BaseRequest execute(Update update) {
        String text = update.message().text();
        long chatId = update.message().chat().id();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Такого пользователя не существует");
        }
        int petUserId = petUser.getId();
        int petId = parseUtil.getIdLink(text);
        Optional<Pet> optionalPet = petService.findById(petId);
        if (optionalPet.isEmpty()) {
            return new SendMessage(chatId, "Такого животного нет");
        }
        Pet pet = optionalPet.get();
        Optional<AvatarPet> avatarPetOptional = avatarPetService.findAvatarPetByPetId(petId);
        if (avatarPetOptional.isEmpty()) {
            String answer = "Данные о животном не загружены \n %s - подать заявку на усыновление";
            return new SendMessage(chatId,
                    String.format(answer, parseUtil.tempParse(PET_TAKE_ID_NUM, petId)));
        }
        AvatarPet avatarPet = avatarPetOptional.get();
        String description = avatarPet.getDescription();
        if (description.isEmpty()) {
            description = "Описание отсутствует";
        }
        byte[] photo = avatarPet.getData();
        String photoAnswer;
        if (photo == null) {
            photoAnswer = "Фото не загружено";
        } else {
            photoAnswer = parseUtil.tempParse(AVATAR_PHOTO_PET_ID_NUM, petId) + " - показать фото";
        }
        String answer = String.format(menu, pet.getName(), photoAnswer, description,
                parseUtil.tempParse(PET_TAKE_ID_NUM, petId));
        return new SendMessage(chatId, answer);
    }

    @Override
    public String getLink() {
        return PET_SHELTER_ID_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }
}

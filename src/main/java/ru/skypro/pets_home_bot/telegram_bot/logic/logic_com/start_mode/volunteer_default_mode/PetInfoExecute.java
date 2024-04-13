package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.AvatarPet;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Volunteer;
import ru.skypro.pets_home_bot.api_bot.service.AvatarPetService;
import ru.skypro.pets_home_bot.api_bot.service.PetService;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.PET_SHELTER_ID_NUM;
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.PET_TAKE_ID_NUM;
@Component
public class PetInfoExecute implements ExecuteMessage {
    private final String menu = """ 
            Питомец по имени %s:
            ---------------------
            Описание:
            %s
            """;

    private final ParseUtil parseUtil;
    private final PetService petService;
    private final VolunteerService volunteerService;
    private final AvatarPetService avatarPetService;

    public PetInfoExecute(ParseUtil parseUtil, PetService petService, VolunteerService volunteerService, AvatarPetService avatarPetService) {
        this.parseUtil = parseUtil;
        this.petService = petService;
        this.volunteerService = volunteerService;
        this.avatarPetService = avatarPetService;
    }

    @Override
    public BaseRequest execute(Update update) {
        String text = update.message().text();
        long chatId = update.message().chat().id();
        Volunteer volunteer = volunteerService.findByChatIdVolunteer(chatId);
        if (volunteer == null) {
            return new SendMessage(chatId, "Такого волонтера не существует");
        }
        int petId = parseUtil.getIdLink(text);
        Optional<Pet> optionalPet = petService.findById(petId);
        if (optionalPet.isEmpty()) {
            return new SendMessage(chatId, "Такого животного нет");
        }
        Pet pet = optionalPet.get();
        Optional<AvatarPet> avatarPetOptional = avatarPetService.findAvatarPetByPetId(petId);
        if (avatarPetOptional.isEmpty()) {
            String answer = "Данные о животном не загружены";
            return new SendMessage(chatId, answer);
        }
        AvatarPet avatarPet = avatarPetOptional.get();
        String description = avatarPet.getDescription();
        if (description == null || description.isEmpty()) {
            description = "Описание отсутствует";
        }
        byte[] photo = avatarPet.getData();
        String answer = String.format(menu, pet.getName(), description);
        if (photo == null) {
            return new SendMessage(chatId, answer);
        }
        return new SendPhoto(chatId, photo).caption(answer);
    }

    @Override
    public String getLink() {
        return PET_SHELTER_ID_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

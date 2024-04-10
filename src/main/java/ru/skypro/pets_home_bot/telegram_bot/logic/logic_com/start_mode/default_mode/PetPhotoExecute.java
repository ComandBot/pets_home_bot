package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.AvatarPet;
import ru.skypro.pets_home_bot.api_bot.service.AvatarPetService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.AVATAR_PHOTO_PET_ID_NUM;
@Component
public class PetPhotoExecute implements ExecuteMessage {

    private final ParseUtil parseUtil;
    private final AvatarPetService avatarPetService;

    public PetPhotoExecute(ParseUtil parseUtil, AvatarPetService avatarPetService) {
        this.parseUtil = parseUtil;
        this.avatarPetService = avatarPetService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        String text = update.message().text();
        int petId = parseUtil.getIdLink(text);
        Optional<AvatarPet> avatarPetOptional =
                avatarPetService.findAvatarPetByPetId(petId);
        if (avatarPetOptional.isEmpty()) {
            return new SendMessage(chatId, "Подробное описание животного не загружено.");
        }
        byte[] photo = avatarPetOptional.get().getData();
        if (photo == null) {
            return new SendMessage(chatId, "Фото животного отсутствует");
        }
        return new SendPhoto(chatId, photo);
    }

    @Override
    public String getLink() {
        return AVATAR_PHOTO_PET_ID_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }
}

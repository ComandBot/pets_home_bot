package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;
import ru.skypro.pets_home_bot.api_bot.service.PetService;
import ru.skypro.pets_home_bot.api_bot.service.ShelterService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;

@Component
public class ShelterPetsListExecute implements ExecuteMessage {

    private final String menu = """
            Список животных в приюта %s:
            """;

    private final ParseUtil parseUtil;
    private final PetService petService;
    private final ShelterService shelterService;

    public ShelterPetsListExecute(ParseUtil parseUtil, PetService petService, ShelterService shelterService) {
        this.parseUtil = parseUtil;
        this.petService = petService;
        this.shelterService = shelterService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        String text = update.message().text();
        int shelterId = parseUtil.getIdLink(text);
        Optional<Shelter> shelterOptional = shelterService.findByShelterId(shelterId);
        if (shelterOptional.isEmpty()) {
            return new SendMessage(chatId, "Нет убежища");
        }
        List<Pet> pets = petService.findAllByShelterAndIdInOwner(shelterId);
        if (pets.isEmpty()) {
            return new SendMessage(chatId, "В приюте свободных животных нет");
        }
        String result = String.format(menu + pets.stream()
                .map(e -> parseUtil.tempParse(PET_SHELTER_ID_NUM, e.getId()) + " - " + e.getName())
                .collect(Collectors.joining("\n")), shelterOptional.get().getNameShelter());
        return new SendMessage(chatId, result);
    }

    @Override
    public String getLink() {
        return SHOW_LIST_PETS_SHELTER_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }
}

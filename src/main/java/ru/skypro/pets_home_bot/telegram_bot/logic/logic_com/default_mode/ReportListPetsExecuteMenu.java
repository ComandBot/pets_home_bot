package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.PetService;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.List;
import java.util.stream.Collectors;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;

@Component
public class ReportListPetsExecuteMenu implements ExecuteMessage {
    private final String menu = """
            Выберите животное из списка для отправки отчета:
            %s
            """;
    private final PetService petService;
    private final PetUserService petUserService;
    private final ParseUtil parseUtil;

    public ReportListPetsExecuteMenu(PetService petService, PetUserService petUserService, ParseUtil parseUtil) {
        this.petService = petService;
        this.petUserService = petUserService;
        this.parseUtil = parseUtil;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id().longValue();
        PetUser petUser = petUserService.findByChatIdPetUser(chatId);
        if (petUser == null) {
            return new SendMessage(chatId, "Вы еще не зарегестрированы как пользователь");
        }
        int petUserId = petUser.getId();
        List<Pet> pets = petService.findByReportPetsList(petUserId);
        if (pets.isEmpty()) {
            return new SendMessage(chatId, "У вас нет усыновленных животных на испытательном сроке");
        }
        String listPets = pets.stream()
                .map(e -> parseUtil.tempParse(REPORT_PET_ID_NUM, e.getId()) + " - " + e.getName())
                .collect(Collectors.joining("\n"));
        return new SendMessage(chatId, String.format(menu, listPets));
    }

    @Override
    public String getLink() {
        return SEND_REPORT;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.DEFAULT;
    }
}

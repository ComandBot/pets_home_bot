package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.service.PetUserService;
import ru.skypro.pets_home_bot.api_bot.service.VolunteerService;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;
@Component
public class SelectSheltersExecuteMenu implements ExecuteMessage {
    private final String menu = """
            Меню выбора приюта:
            %s - показать список приютов для котов
            %s - показать список приютов для собак
            """;
    private final PetUserService petUserService;
    private final VolunteerService volunteerService;

    public SelectSheltersExecuteMenu(PetUserService petUserService, VolunteerService volunteerService) {
        this.petUserService = petUserService;
        this.volunteerService = volunteerService;
    }

    @Override
    public String execute(Update update) {
        long chatId = update.message().chat().id();
        if (volunteerService.findByChatIdVolunteer(chatId) != null) {
            return "Вы являетесь волнтером. Войдите как волонтер.";
        }
        if (petUserService.findByChatIdPetUser(chatId) == null) {
            PetUser petUser = new PetUser();
            petUser.setChatId(chatId);
            petUserService.add(petUser);
        }
        return String.format(menu, SHELTERS_CATS, SHELTERS_DOGS);
    }

    @Override
    public String getLink() {
        return PET_USER;
    }
}

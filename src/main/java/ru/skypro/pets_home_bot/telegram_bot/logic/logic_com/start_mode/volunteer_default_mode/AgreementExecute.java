package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.service.AvatarPetService;
import ru.skypro.pets_home_bot.api_bot.service.OwnerService;
import ru.skypro.pets_home_bot.api_bot.service.PetService;
import ru.skypro.pets_home_bot.api_bot.service.ReportService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.AGREEMENT_NUM_NUM;
@Component
public class AgreementExecute implements ExecuteMessage {

    private final ParseUtil parseUtil;
    private final OwnerService ownerService;
    private final ReportService reportService;
    private final PetService petService;
    private final AvatarPetService avatarPetService;

    public AgreementExecute(ParseUtil parseUtil, OwnerService ownerService, ReportService reportService, PetService petService, AvatarPetService avatarPetService) {
        this.parseUtil = parseUtil;
        this.ownerService = ownerService;
        this.reportService = reportService;
        this.petService = petService;
        this.avatarPetService = avatarPetService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        String text = update.message().text();
        int[] petUserIdPetId = parseUtil.gePetUserIdAndPetId(text);
        Optional<Owner> optionalOwner = ownerService.findByPetUserIdAndPetId(petUserIdPetId[0], petUserIdPetId[1]);
        if (optionalOwner.isEmpty()) {
            return new SendMessage(chatId, "Такой владелец не найден");
        }
        Owner owner = optionalOwner.get();
        reportService.deleteAllByOwner(owner);
        ownerService.deleteByOwnerId(owner.getOwnerId());
        String link = parseUtil.parseTwoLink(text);
        String answer = "Вы не прошли испытательный срок питомец врзвращается в приют";
        if (AGREEMENT_NUM_NUM.equals(link)) {
            avatarPetService.deleteByPet(owner.getOwnerId().getPet());
            petService.deleteById(petUserIdPetId[1]);
            answer = "Поздравляем! Вы прошли испытательный срок и забрали питомца домой";
        }
        return new SendMessage(owner.getOwnerId().getPetUser().getChatId(), answer);
    }

    @Override
    public String getLink() {
        return AGREEMENT_NUM_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

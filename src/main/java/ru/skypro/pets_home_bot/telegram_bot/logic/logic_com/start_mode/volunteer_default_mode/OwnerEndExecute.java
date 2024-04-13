package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Contact;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
import ru.skypro.pets_home_bot.api_bot.model.Pet;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.projections.ReportIdDate;
import ru.skypro.pets_home_bot.api_bot.service.AvatarPetService;
import ru.skypro.pets_home_bot.api_bot.service.ContactService;
import ru.skypro.pets_home_bot.api_bot.service.OwnerService;
import ru.skypro.pets_home_bot.api_bot.service.ReportService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;
@Component
public class OwnerEndExecute implements ExecuteMessage {
    private final String template = """
            Информация о владельце
            Имя: %s
            Фамилия: %s
            Username: %s
            Телефоны: %s
            Испытательный срок: %d
            Дата усыновления: %s
            ----------------------
            %s - посмотреть сведения об усыновленном животном
            ----------------------
            Список отчетов усыновителя за испытательный срок:
            %s
            ----------------------
            %s - увеличить испытательный срок на 14 дней
            %s - увеличить испытательный срок на 30 дней
            %s
            """;

    private final String agree = "%s - дать согласие на усыновление";
    private final String degree = "%s - изъять животное";

    private final ParseUtil parseUtil;
    private final ContactService contactService;
    private final OwnerService ownerService;
    private final ReportService reportService;

    public OwnerEndExecute(ParseUtil parseUtil,
                           ContactService contactService,
                           OwnerService ownerService,
                           ReportService reportService) {
        this.parseUtil = parseUtil;
        this.contactService = contactService;
        this.ownerService = ownerService;
        this.reportService = reportService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        String text = update.message().text();
        int[] userIdAndPetId = parseUtil.gePetUserIdAndPetId(text);
        Optional<Owner> ownerOptional = ownerService.findByPetUserIdAndPetId(userIdAndPetId[0], userIdAndPetId[1]);
        if (ownerOptional.isEmpty()) {
            return new SendMessage(chatId, "Такой владелец не найден");
        }
        Owner owner = ownerOptional.get();
        PetUser petUser = owner.getOwnerId().getPetUser();
        String nameOwner = petUser.getFirstName();
        if (nameOwner == null) {
            nameOwner = "Имя не задано";
        }
        String lastName = petUser.getLastName();
        if (lastName == null) {
            lastName = "Фамилия не задана";
        }
        String userName = petUser.getUserName();
        if (userName == null) {
            userName = "Username не задано";
        }
        String strContact = "Номер телефона не задан";
        List<Contact> contacts = contactService.findAllByPetUser(petUser);
        if (contacts != null && !contacts.isEmpty()) {
            strContact = contacts.stream()
                    .map(Contact::getPhoneNumber)
                    .collect(Collectors.joining(", "));
        }
        int testPeriod = owner.getTestPeriod();
        String strDateDelivery = owner.getDateDelivery().format(DateTimeFormatter.ISO_DATE);
        Pet pet = owner.getOwnerId().getPet();
        String linkPet = parseUtil.tempParse(PET_SHELTER_ID_NUM, pet.getId());
        String strReports = "Отчеты отсутствуют";
        List<ReportIdDate> reportIdDates =
                reportService.findAllByPetUserIdAndPetId(petUser.getId(), pet.getId());
        if (reportIdDates != null && !reportIdDates.isEmpty()) {
            strReports = reportIdDates.stream()
                    .map(e -> parseUtil.tempParse(REPORT_ID_NUM, e.getId()) + " - "
                            + e.getDateReport().format(DateTimeFormatter.ISO_DATE))
                    .collect(Collectors.joining("\n"));
        }
        String agreeRes = String.format(agree,
                parseUtil.tempParseTwoNum(AGREEMENT_NUM_NUM, petUser.getId(), pet.getId()));
        if (LocalDateTime.now().isBefore(owner.getDateDelivery().plusDays(owner.getTestPeriod())) ) {
            agreeRes = String.format(degree,
                    parseUtil.tempParseTwoNum(SEIZE_NUM_NUM, petUser.getId(), pet.getId()));
        }
        String answer = String.format(template, nameOwner, lastName, userName, strContact,
                testPeriod, strDateDelivery, linkPet,
                strReports, parseUtil.tempParseTwoNum(INCREASE_FOURTEEN_NUM_NUM, petUser.getId(), pet.getId()),
                parseUtil.tempParseTwoNum(INCREASE_THIRTY_NUM_NUM, petUser.getId(), pet.getId()),
                agreeRes);
        return new SendMessage(chatId, answer);
    }

    @Override
    public String getLink() {
        return OWNER_END_USER_PET_NUM_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

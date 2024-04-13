package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Contact;
import ru.skypro.pets_home_bot.api_bot.model.PetUser;
import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.service.ContactService;
import ru.skypro.pets_home_bot.api_bot.service.ReportService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.*;

@Component
public class ShowReportExecute implements ExecuteMessage {
    private final String template = """
            Отчет от пользователя на дату %s
            Статус отчета: %s
            Контактная информация пользователя:
            %s
            -----------------
            Описание рациона питания:
            %s
            -----------------
            Обшее состояние и самочувствие:
            %s
            -----------------
            Изменение в поведении:
            %s
            -----------------
            %s - отметка о просмотре отчета
            %s - написать замечание по отчету
            """;

    private final ParseUtil parseUtil;

    private final ReportService reportService;
    private final ContactService contactService;

    public ShowReportExecute(ParseUtil parseUtil, ReportService reportService, ContactService contactService) {
        this.parseUtil = parseUtil;
        this.reportService = reportService;
        this.contactService = contactService;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        String text = update.message().text();
        int idReport = parseUtil.getIdLink(text);
        Optional<Report> reportOptional = reportService.findById(idReport);
        if(reportOptional.isEmpty()) {
            return new SendMessage(chatId, "Отчет удален");
        }
        Report report = reportOptional.get();
        String status = "не просмотрен";
        if (report.isViewed()) {
            status = "просмотрен";
        }
        PetUser petUser = report.getOwner().getOwnerId().getPetUser();
        List<Contact> contacts = contactService.findAllByPetUser(petUser);
        String textContact = "Контактная информация не обнаружена";
        if (contacts != null && !contacts.isEmpty()) {
            textContact = contacts.stream()
                    .map(Contact::getPhoneNumber)
                    .collect(Collectors.joining("\n"));
        }
        String answer = String.format(template, report.getDateReport().format(DateTimeFormatter.ISO_DATE), status,
                textContact, report.getDiet(), report.getCondition(), report.getBehaviour(),
                parseUtil.tempParse(VIEWED_REPORT_ID_NUM, report.getId()),
                parseUtil.tempParse(MESSAGE_USER_ID_NUM, petUser.getId()));
        return new SendPhoto(chatId, report.getPhoto()).caption(answer);
    }

    @Override
    public String getLink() {
        return REPORT_ID_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

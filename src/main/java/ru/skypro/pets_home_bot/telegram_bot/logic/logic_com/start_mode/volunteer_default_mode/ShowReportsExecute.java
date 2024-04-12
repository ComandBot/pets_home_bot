package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.projections.ReportIdDate;
import ru.skypro.pets_home_bot.api_bot.service.ReportService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.REPORTS;
import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.REPORT_ID_NUM;

@Component
public class ShowReportsExecute implements ExecuteMessage {
    private final String template = """
            Список не просмотренных отчетов:
            %s
            """;
    private final ReportService reportService;

    private final ParseUtil parseUtil;

    public ShowReportsExecute(ReportService reportService, ParseUtil parseUtil) {
        this.reportService = reportService;
        this.parseUtil = parseUtil;
    }

    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        List<ReportIdDate> reportIdDates = reportService.findAllByIsViewedFalseProjection();
        if (reportIdDates == null || reportIdDates.isEmpty()) {
            return new SendMessage(chatId, "Не просмотренных отчетов нет");
        }
        String reportStr = reportIdDates.stream()
                .map(e -> parseUtil.tempParse(REPORT_ID_NUM, e.getId()) + " - " + e.getDateReport().format(DateTimeFormatter.ISO_DATE))
                .collect(Collectors.joining("\n"));
        String answer = String.format(template, reportStr);
        return new SendMessage(chatId, answer);
    }

    @Override
    public String getLink() {
        return REPORTS;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

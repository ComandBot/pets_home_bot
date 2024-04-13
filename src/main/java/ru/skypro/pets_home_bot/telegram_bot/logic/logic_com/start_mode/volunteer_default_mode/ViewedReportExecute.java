package ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.start_mode.volunteer_default_mode;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.enums.MessageMode;
import ru.skypro.pets_home_bot.api_bot.model.Report;
import ru.skypro.pets_home_bot.api_bot.service.ReportService;
import ru.skypro.pets_home_bot.telegram_bot.logic.logic_com.ExecuteMessage;
import ru.skypro.pets_home_bot.telegram_bot.logic.utils.ParseUtil;

import java.util.Optional;

import static ru.skypro.pets_home_bot.telegram_bot.logic.constants.Link.VIEWED_REPORT_ID_NUM;

@Component
public class ViewedReportExecute implements ExecuteMessage {
    private final ParseUtil parseUtil;
    private final ReportService reportService;

    public ViewedReportExecute(ParseUtil parseUtil, ReportService reportService) {
        this.parseUtil = parseUtil;
        this.reportService = reportService;
    }


    @Override
    public BaseRequest execute(Update update) {
        long chatId = update.message().chat().id();
        String text = update.message().text();
        int idReport = parseUtil.getIdLink(text);
        Optional<Report> optionalReport = reportService.findById(idReport);
        if (optionalReport.isEmpty()) {
            return new SendMessage(chatId, "Такой отчет не найден");
        }
        Report report = optionalReport.get();
        report.setViewed(true);
        reportService.add(report);
        return new SendMessage(chatId, "Отчет просмотрен");
    }

    @Override
    public String getLink() {
        return VIEWED_REPORT_ID_NUM;
    }

    @Override
    public MessageMode getMessageMode() {
        return MessageMode.VOLUNTEER_DEFAULT;
    }
}

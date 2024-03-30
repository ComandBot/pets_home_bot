package ru.skypro.pets_home_bot.telegram_bot.logic.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class ParseUtil {

    private final String num = "num";
    private final String regex = "\\d+";
    private final String regexPhone = "^\\+7\\-9\\d{2}\\-\\d{3}\\-\\d{2}\\-\\d{2}$";
    private static final String phoneRegex = "";
    public String parseLink(String link) {
        return link.replaceAll(regex, num);
    }

    public String tempParse(String template, int id) {
        return template.replaceAll("num", String.valueOf(id));
    }

    public int getIdLink(String link) {
        return Integer.parseInt(link.split("_")[1]);
    }

    public boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile(regexPhone);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}

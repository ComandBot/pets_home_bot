package ru.skypro.pets_home_bot.telegram_bot.logic.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.skypro.pets_home_bot.api_bot.model.OwnerId;

@Component
@Slf4j
public class ParseUtil {
    private final String num = "num";
    private final String regex = "\\d+";
    private final String regexPhone = "^\\+7\\-9\\d{2}\\-\\d{3}\\-\\d{2}\\-\\d{2}$";

    public String parseLink(String link) {
        return link.replaceAll(regex, num);
    }

    public String tempParse(String template, int id) {
        return template.replaceAll("num", String.valueOf(id));
    }

    public String tempParseTwoNum(String template, int petUserId, int petId) {
        return template.replaceFirst(num, String.valueOf(petUserId))
                .replaceFirst(num, String.valueOf(petId));
    }

    public String parseTwoLink(String link) {
        return link.replaceFirst(regex, num).replaceFirst(regex, num);
    }

    public int getIdLink(String link) {
        int result = 0;
        try {
            result = Integer.parseInt(link.split("_")[1]);
        } catch (NumberFormatException e) {
            log.error("Ошибка форматирования");
        }
        return result;
    }

    public int[] gePetUserIdAndPetId(String link) {
        String[] strings = link.split("_");
        int[] res = new int[2];
        try {
            res[0] = Integer.parseInt(strings[1]);
            res[1] = Integer.parseInt(strings[2]);
        } catch (NumberFormatException e) {
            res = new int[2];
            log.error("Ошибка форматирования");
        }
        return res;
    }

    public boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile(regexPhone);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}

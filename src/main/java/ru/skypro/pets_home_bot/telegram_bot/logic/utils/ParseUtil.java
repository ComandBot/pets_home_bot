package ru.skypro.pets_home_bot.telegram_bot.logic.utils;

import org.springframework.stereotype.Component;

@Component
public class ParseUtil {

    private static final String NUM = "num";
    private static final String regex = "\\d+";
    public String parseLink(String link) {
        return link.replaceAll(regex, NUM);
    }

    public String tempParse(String template, int id) {
        return template.replaceAll("num", String.valueOf(id));
    }

    public int getIdLink(String link) {
        return Integer.parseInt(link.split("_")[1]);
    }

}

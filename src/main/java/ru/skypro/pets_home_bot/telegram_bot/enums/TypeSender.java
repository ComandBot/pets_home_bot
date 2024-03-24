package ru.skypro.pets_home_bot.telegram_bot.enums;

public enum TypeSender {
    START("/start"),
    SHELTER_SELECT_CATS("/shelters_cats", "выберите приют для кошек"),
    SHELTER_SELECT_DOGS("/shelters_dogs", "выберите приют для собак"),
    SHELTER("/shelter"),
    SHELTER_INFO("/shelter-info"),
    END("/end", "desc");

    private final String link;
    private String description;

    TypeSender(String link) {
        this.link = link;
    }
    TypeSender(String link, String description) {
        this(link);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }
}

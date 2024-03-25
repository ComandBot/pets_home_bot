package ru.skypro.pets_home_bot.telegram_bot.logic.enums;

public enum TypeSender {
    START("/start"),
    NO_BEAN(""),
    PET_USER("/petuser", "войти как усыновитель"),
    VOLUNTEER("/volunteer", "войти как волонтер"),
    SHELTER_SELECT_CATS("/shelters_cats", "показать список приютов для кошек"),
    SHELTER_SELECT_DOGS("/shelters_dogs", "показать список приютов для собак"),
    REGISTER("/register", "новая регистрация"),
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

    @Override
    public String toString() {
        return link + " - " + description;
    }
}

package ru.skypro.pets_home_bot.telegram_bot.logic.constants;

public class TemplateAnswer {
    public static final String AUTHORIZATION_MENU = """
            Авторизируйтесь:
            %s
            %s
            """;
    public static final String PET_USER_SELECT_TYPE_SHELTER = """
            Выбор приюта:
            %s
            %s
            """;

    public static final String SHELTERS_LIST_CATS = "Список приютов для кошек:\n";
    public static final String SHELTERS_LIST_DOGS = "Список приютов для собак:\n";
}

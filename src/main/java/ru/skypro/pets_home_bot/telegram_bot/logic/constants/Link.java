package ru.skypro.pets_home_bot.telegram_bot.logic.constants;

public class Link {
    public static final String START = "/start";

    /**
     * start - menu
     */
    public static final String PET_USER = "/petuser";
    public static final String VOLUNTEER = "/volunteer";

    /**
     * /petuser - меню
     */
    public static final String SHELTERS_CATS = "/shelterscats";
    public static final String SHELTERS_DOGS = "/sheltersdogs";

    /**
     * /shelterscats или /sheltersdogs - меню списка приютов
     */
    public static final String SHELTER_NUM = "/shelter_num";

    /**
     * /shelter_num - меню приюта для котов или собак
     */
    public static final String SHELTER_INFOS_NUM = "/shelterinfos_num";
    public static final String SHELTER_TAKE_INFOS_NUM = "/sheltertakeinfos_num";
    public static final String SEND_REPORT = "/sendreport";
    public static final String HELP = "/help";

    /**
     * /shelterinfos_num - меню списка и названия информации
     */
    public static final String CONTACT = "/contact";
    public static final String SHELTER_INFO_ID_NUM = "/shelterinfoid_num";
    public static final String SHELTER_SCHEME_NUM = "/shelterscheme_num";



}

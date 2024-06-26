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
    /**
     * /shelterscheme_num - получить схему подъезда
     */
    public static final String CANSEL_CONTACT = "/canselcontact";
    public static final String DEFAULT_TEXT = "defaulttext";
    public static final String SHOW_LIST_PETS_SHELTER_NUM = "/showpetslistshelter_num";
    public static final String SHELTER_TAKE_INFO_ID_NUM = "/sheltertakeinfoid_num";
    public static final String PET_SHELTER_ID_NUM = "/petshelterid_num";
    public static final String AVATAR_PHOTO_PET_ID_NUM = "/avatarphotopetid_num";
    public static final String PET_TAKE_ID_NUM = "/takepetid_num";
    public static final String REPORT_PET_ID_NUM = "/reportpetid_num";
    public static final String PHOTO_REPORT_PET = "/photoreportpet";
    public static final String DIET_REPORT_PET = "/dietreportpet";
    public static final String  CONDITION_PET = "/conditionpet";
    public static final String BEHAVIOR_PET = "/behaviorpet";
    public static final String REPORT_CONFIRM = "/confirm";
    public static final String CANSEL_REPORT = "/canselreport";
    public static final String CANSEL_REPORT_PHOTO = "/canselreportphoto";
    public static final String CANSEL_REPORT_DIET = "/canselreportdiet";
    public static final String CANSEL_REPORT_CONDITION = "/canselreportcondition";
    public static final String CANSEL_REPORT_BEHAVIOR = "/canselreportbehavior";
    public static final String REPORTS = "/reports";
    public static final String ORDERS = "/orders";
    public static final String SOLUTION = "/solution";
    public static final String CANSEL_COMMUNICATION = "/canselcommunication";
    public static final String CANSEL_USER_COMMUNICATION = "/canseluser";
    public static final String ANSWER_PET_USER = "/answer";
    public static final String OWNER_USER_ID_PET_ID_NUM1_NUM2 = "/owner_num_num";
    public static final String YES_ADOPTION_USER_PET_NUM1_NUM2 = "/yesadoption_num_num";
    public static final String NO_ADOPTION_USER_PET_NUM1_NUM2 = "/noadoption_num_num";
    public static final String REPORT_ID_NUM = "/reportid_num";
    public static final String VIEWED_REPORT_ID_NUM = "/viewed_num";
    public static final String MESSAGE_USER_ID_NUM = "/messageuserid_num";

    public static final String OWNERS_END = "/ownersend";
    public static final String OWNERS_CUR = "/ownerscur";

    public static final String OWNER_END_USER_PET_NUM_NUM = "/ownerend_num_num";
    public static final String INCREASE_FOURTEEN_NUM_NUM = "/increasefourteen_num_num";
    public static final String INCREASE_THIRTY_NUM_NUM = "/increasethirty_num_num";
    public static final String AGREEMENT_NUM_NUM = "/agreement_num_num";
    public static final String SEIZE_NUM_NUM = "/seize_num_num";
}

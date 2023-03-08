package pro.sky.telegrambotteamwork.constants;

import java.util.List;

public class KeyboardMessageUserConstant {
    // Первое общение с ботом
    public static final String SUBSCRIPTION = "Подписаться";
    // Выбор питомца
    public static final String DOG = "\uD83D\uDC36";
    public static final String CAT = "\uD83D\uDC31";
    public static final String ANOTHER_PET = "Выбрать другого питомца";
    // Меню, после выбора питомца
    public static final String INFORMATION_ABOUT_THE_SHELTER_DOG = "Узнать информацию о приюте собак";
    public static final String TAKE_A_PET_FROM_A_SHELTER_DOG = "Как взять собаку из приюта";
    public static final String PET_REPORT_DOG = "Прислать отчет о собаке";
    public static final String CALL_A_VOLUNTEER_DOG = "Позвать волонтера для собак";
    public static final String INFORMATION_ABOUT_THE_SHELTER_CAT = "Узнать информацию о приюте кошек";
    public static final String TAKE_A_PET_FROM_A_SHELTER_CAT = "Как взять кошку из приюта";
    public static final String PET_REPORT_CAT = "Прислать отчет о кошке";
    public static final String CALL_A_VOLUNTEER_CAT = "Позвать волонтера для кошек";
    // Информационное меню о питомнике
    public static final String ABOUT_OUR_NURSERY = "О нашем питомнике";
    public static final String AMBULANCE_FOR_ANIMALS = "Скорая помощь для животных";
    public static final String INSTRUCTIONS_FOR_CALLING_AN_AMBULANCE = "Скорой помощь для питомца";
    public static final String REHABILITATION_FOR_SPECIAL_ANIMALS = "Реабилитация для питомца";
    public static final String REQUISITES = "Реквизиты";
    public static final String CONTACTS = "Контакты";
    // Меню о том, как взять питомца из приюта
    public static final String ARE_PETS_IN_SHELTER_HEALTHY = "Здоровье животного в питомнике";
    public static final String YOU_DECIDED_TO_TAKE_PET = "Вы решились взять животное?";
    public static final String IF_YOU_ALREADY_HAVE_PET = "Если у вас уже есть питомец";
    public static final String PET_TRANSFER_PROCEDURE = "Процедура передачи животного";
    // Списки кнопок
    public static final List<String> SUBSCRIPTION_MENU = List.of(
            SUBSCRIPTION
    );
    public static final List<String> CHOOSING_PET_MENU = List.of(
            DOG,
            CAT
    );
    public static final List<String> MAIN_DOG_MENU = List.of(
            INFORMATION_ABOUT_THE_SHELTER_DOG,
            TAKE_A_PET_FROM_A_SHELTER_DOG,
            PET_REPORT_DOG,
            CALL_A_VOLUNTEER_DOG
    );
    public static final List<String> MAIN_CAT_MENU = List.of(
            INFORMATION_ABOUT_THE_SHELTER_CAT,
            TAKE_A_PET_FROM_A_SHELTER_CAT,
            PET_REPORT_CAT,
            CALL_A_VOLUNTEER_CAT
    );
    public static final List<String> INFORMATION_MENU = List.of(
            ABOUT_OUR_NURSERY,
            AMBULANCE_FOR_ANIMALS,
            INSTRUCTIONS_FOR_CALLING_AN_AMBULANCE,
            REHABILITATION_FOR_SPECIAL_ANIMALS,
            REQUISITES,
            CONTACTS
    );
    public static final List<String> TAKE_A_PET_FROM_A_SHELTER_MENU = List.of(
            ARE_PETS_IN_SHELTER_HEALTHY,
            YOU_DECIDED_TO_TAKE_PET,
            IF_YOU_ALREADY_HAVE_PET,
            PET_TRANSFER_PROCEDURE
    );
}

package pro.sky.telegrambotteamwork.listeners;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotteamwork.model.User;
import pro.sky.telegrambotteamwork.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static pro.sky.telegrambotteamwork.constants.CommandMessageUserConstant.START;
import static pro.sky.telegrambotteamwork.constants.KeyboardMessageUserConstant.*;
import static pro.sky.telegrambotteamwork.constants.TextMessageUserConstant.*;

/**
 * Основной класс с логикой телеграм-бота.
 * Этот класс расширяет {@link UpdatesListener}
 */
@Service
@Data
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot telegramBot;
    private final UserRepository userRepository;


    /**
     * Метод, который вызывается сразу после инициализации свойств
     */
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Основной метод класса {@link TelegramBotUpdatesListener}.
     * Обрабатывает сообщения пользователю
     *
     * @param updates Параметр входящего обновления
     * @return Возвращает все обновления
     */
    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {
                logger.info("Запрос от пользователя: {}", update);
                Message messageUser = update.message();

                if (hasMessage(update)) {
                    if (hasText(update)) {
                        if (START.equals(messageUser.text())) {
                            telegramBot.execute(loadingTheMenu(messageUser, SUBSCRIBE_TO_BOT_MESSAGE, SUBSCRIPTION_MENU));
                        }
                    }
                } else if (hasCallbackQuery(update)) {
                    if (SUBSCRIPTION.equals(update.callbackQuery().data())) {
                        telegramBot.execute(loadingTheMenuDogAndCat(update, WELCOME_MESSAGE, CHOOSING_PET_MENU));
                    }
                }

                if (hasCallbackQuery(update)) {
                    if (DOG.equals(update.callbackQuery().data())) {
                        telegramBot.execute(loadingTheMenuCallbackQuery(update, DOG_MESSAGE, MAIN_DOG_MENU));
                    } else {
                        mainMenuDog(update);
                    }
                }

                if (hasCallbackQuery(update)) {
                    if (CAT.equals(update.callbackQuery().data())) {
                        telegramBot.execute(loadingTheMenuCallbackQuery(update, CAT_MESSAGE, MAIN_CAT_MENU));
                    } else {
                        mainMenuCat(update);
                    }
                }
                if (hasCallbackQuery(update)) {
                    informationMenu(update);
                }
                if (hasCallbackQuery(update)) {
                    takePetMenu(update);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * Метод, который сохраняет пользователя в базу данных
     *
     * @param update входящее обновление
     */
    private void saveUser(Update update) {
        if (update.message().contact() != null) {
            String firstName = update.message().contact().firstName();
            String lastName = update.message().contact().lastName();
            String userName = update.message().chat().username();
            String phone = update.message().contact().phoneNumber();
            Long userId = update.message().from().id();
            Long chatId = update.message().chat().id();
            LocalDateTime dateTime = LocalDateTime.now();
            List<User> usersIds = userRepository.findAll().stream().filter(id -> id.getUserId().equals(userId)).collect(Collectors.toList());
            if (!(usersIds.equals(userId))) {
                telegramBot.execute(new SendMessage(chatId, "Вы подписаны на нашего бота"));
                return;
            }
            if (usersIds.equals(userId)) {
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setUserName(userName);
                user.setPhone(phone);
                user.setUserId(userId);
                user.setDateTime(dateTime);
                userRepository.save(user);
                telegramBot.execute(new SendMessage(chatId, "Вы только что подписались на нашего бота! Поздравляем!"));
                logger.info("Ползователь сохранен в базе данных: {}", user);
            }
        }
    }

    /**
     * Метод, генерирующий список кнопок
     *
     * @param listOfButton обязательный параметр всех кнопок
     * @return Возвращает сгенерированную клавиатуру
     */
    private InlineKeyboardMarkup keyboardGeneration(List<String> listOfButton) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        for (String list : listOfButton) {
            inlineKeyboardMarkup.addRow(new InlineKeyboardButton(list).callbackData(list));
        }

        return inlineKeyboardMarkup;
    }

    /**
     * Метод, генерирующий список кнопок для собак, кошек и других пиомцев
     *
     * @param listOfButton обязательный параметр всех кнопок
     * @return Возвращает сгенерированную клавиатуру
     */
    private InlineKeyboardMarkup keyboardDogAndCat(List<String> listOfButton) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(DOG).callbackData(DOG),
                        new InlineKeyboardButton(CAT).callbackData(CAT)
                }
        );
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton(ANOTHER_PET).callbackData(ANOTHER_PET));

        return inlineKeyboardMarkup;
    }

    /**
     * Этот метод загружает меню под текстовое сообщение
     *
     * @param message      вся необходимая информация о пользователе, написавшем сообщение боту
     * @param messageText  текстовое сообщение
     * @param listOfButton список кнопок
     * @return Отправленное новое сообщение от бота
     */
    public SendMessage loadingTheMenu(Message message, String messageText, List<String> listOfButton) {
        Keyboard keyboard = keyboardGeneration(listOfButton);
        return new SendMessage(message.chat().id(), messageText).replyMarkup(keyboard);
    }

    /**
     * Этот метод загружает вложенное меню
     *
     * @param update       входящее обновление
     * @param messageText  текстовое сообщение
     * @param listOfButton список кнопок
     * @return Отправленное новое сообщение от бота
     */
    private SendMessage loadingTheMenuCallbackQuery(Update update, String messageText, List<String> listOfButton) {
        Keyboard keyboard = keyboardGeneration(listOfButton);
        return new SendMessage(update.callbackQuery().message().chat().id(), messageText).replyMarkup(keyboard);
    }

    /**
     * Этот метод загружает вложенное меню для собак и кошек
     *
     * @param update       входящее обновление
     * @param messageText  текстовое сообщение
     * @param listOfButton список кнопок
     * @return Отправленное новое сообщение от бота
     */
    private SendMessage loadingTheMenuDogAndCat(Update update, String messageText, List<String> listOfButton) {
        Keyboard keyboard = keyboardDogAndCat(listOfButton);
        return new SendMessage(update.callbackQuery().message().chat().id(), messageText).replyMarkup(keyboard);
    }

    /**
     * Этот метод проверяет есть ли запрос информации от пользователя
     *
     * @param update входящее обновление
     * @return Возвращает true, если есть информация от пользователя
     */
    private boolean hasMessage(Update update) {
        return update.message() != null;
    }

    /**
     * Этот метод проверяет есть ли запрос обратного вызова
     *
     * @param update входящее обновление
     * @return Возвращает true, если есть запрос обратного вызова
     */
    private boolean hasCallbackQuery(Update update) {
        return update.callbackQuery() != null;
    }

    /**
     * Этот метод проверяет есть ли запрос контактной информации пользователя
     *
     * @param update входящее обновление
     * @return Возвращает true, если есть запрос контактной информации пользователя
     */
    private boolean hasContact(Update update) {
        return update.callbackQuery().message().contact() != null;
    }

    /**
     * Этот метод проверяет есть ли запрос текстовой информации пользователя
     *
     * @param update входящее обновление
     * @return Возвращает true, если есть запрос текстовой информации пользователя
     */
    private boolean hasText(Update update) {
        return update.message().text() != null;
    }

    /**
     * Этот метод проверяет есть ли сообщение от пользователя, через нажатие кнопки
     *
     * @param update входящее обновление
     * @return Возвращает true, если сообщение от пользователя, через нажатие кнопки
     */
    private boolean hasMessageCallbackQuery(Update update) {
        return update.callbackQuery().message() != null;
    }

    /**
     * Метод выводит сообщения пользователю
     *
     * @param chatId идентификатор чата
     * @param text   текстовое сообщение
     */
    public void sendMessage(Long chatId, String text) {
        SendResponse response = telegramBot.execute(new SendMessage(chatId, text));
    }

    /**
     * Этот метод выводит главное меню для собак
     *
     * @param update входящее обновление
     */
    private void mainMenuDog(Update update) {
        if (INFORMATION_ABOUT_THE_SHELTER_DOG.equals(update.callbackQuery().data())) {
            telegramBot.execute(loadingTheMenuCallbackQuery(update, INFORMATION_ABOUT_THE_SHELTER_MESSAGE, INFORMATION_MENU));
        } else if (TAKE_A_PET_FROM_A_SHELTER_DOG.equals(update.callbackQuery().data())) {
            telegramBot.execute(loadingTheMenuCallbackQuery(update, TAKE_A_PET_FROM_A_SHELTER_MESSAGE, TAKE_A_PET_FROM_A_SHELTER_MENU));
        } else if (PET_REPORT_DOG.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Здесь будет информация о том, как прислать отчет о собаке"));
        } else if (CALL_A_VOLUNTEER_DOG.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Здесь будет информация о том, как позвать волонтера"));
        }
    }

    /**
     * Этот метод выводит главное меню для кошек
     *
     * @param update входящее обновление
     */
    private void mainMenuCat(Update update) {
        if (INFORMATION_ABOUT_THE_SHELTER_CAT.equals(update.callbackQuery().data())) {
            telegramBot.execute(loadingTheMenuCallbackQuery(update, INFORMATION_ABOUT_THE_SHELTER_MESSAGE, INFORMATION_MENU));
        } else if (TAKE_A_PET_FROM_A_SHELTER_CAT.equals(update.callbackQuery().data())) {
            telegramBot.execute(loadingTheMenuCallbackQuery(update, TAKE_A_PET_FROM_A_SHELTER_MESSAGE, TAKE_A_PET_FROM_A_SHELTER_MENU));
        } else if (PET_REPORT_CAT.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Здесь будет информация о том, как прислать отчет о кошке"));
        } else if (CALL_A_VOLUNTEER_CAT.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Здесь будет информация о том, как позвать волонтера"));
        }
    }

    /**
     * Этот метод выводит информационное меню о питомце
     *
     * @param update входящее обновление
     */
    private void informationMenu(Update update) {
        if (ABOUT_OUR_NURSERY.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), ABOUT_OUR_NURSERY_MESSAGE));
        } else if (AMBULANCE_FOR_ANIMALS.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), AMBULANCE_FOR_ANIMALS_MESSAGE));
        } else if (INSTRUCTIONS_FOR_CALLING_AN_AMBULANCE.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), INSTRUCTIONS_FOR_CALLING_AN_AMBULANCE_MESSAGE));
        } else if (REHABILITATION_FOR_SPECIAL_ANIMALS.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), REHABILITATION_FOR_SPECIAL_ANIMALS_MESSAGE));
        } else if (REQUISITES.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), REQUISITES_MESSAGE));
        } else if (CONTACTS.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), CONTACTS_MESSAGE));
        }
    }

    /**
     * Этот метод выводит меню о том, как взять пиомца из приюта
     *
     * @param update входящее обновление
     */
    private void takePetMenu(Update update) {
        if (ARE_PETS_IN_SHELTER_HEALTHY.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), ARE_PET_IN_SHELTER_HEALTHY_MESSAGE));
        } else if (YOU_DECIDED_TO_TAKE_PET.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), YOU_DECIDED_TO_TAKE_PET_MESSAGE));
        } else if (IF_YOU_ALREADY_HAVE_PET.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), IF_YOU_ALREADY_HAVE_PET_MESSAGE));
        } else if (PET_TRANSFER_PROCEDURE.equals(update.callbackQuery().data())) {
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), PET_TRANSFER_PROCEDURE_MESSAGE));
        }
    }

}
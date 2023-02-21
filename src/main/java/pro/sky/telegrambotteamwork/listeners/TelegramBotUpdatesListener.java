package pro.sky.telegrambotteamwork.listeners;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static pro.sky.telegrambotteamwork.constants.UserRequestConstant.WELCOME_MESSAGE;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    // Объявление и инициализация логгера, для отслеживания событий
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    // Объявление класса TelegramBot
    private final TelegramBot telegramBot;

    // Инжекция класса TelegramBot (объявление конструктора)
    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    // Метод, который вызывается сразу после инициализации свойств
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Запрос от пользователя: {}", update);
            // Объявление и инициализация переменной с текстовым сообщением
            String message = update.message().text();
            // Объявление и инициализация переменной с id чатом
            long chatId = update.message().chat().id();
            // Объявление и инициализация переменной с именем пользователя
            String name = update.message().chat().firstName();

            switch (message) {
                case "/start":
                    sendMessage(chatId, "Здравствуйте " + name + "! " + WELCOME_MESSAGE);
                    break;
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    // Метод, который выводит сообщения пользователю и кнопки команд
    private void sendMessage(long chatId, String message) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("11111"),
                new KeyboardButton("22222"),
                new KeyboardButton("33333"),
                new KeyboardButton("44444"));
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(false);
        replyKeyboardMarkup.selective(false);

        SendMessage sendMessage = new SendMessage(chatId, message)
                .replyMarkup(replyKeyboardMarkup)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);

        SendResponse sendResponse = telegramBot.execute(sendMessage);
    }
}

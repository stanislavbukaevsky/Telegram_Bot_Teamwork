package pro.sky.telegrambotteamwork.listeners;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static pro.sky.telegrambotteamwork.constants.UserRequestConstant.*;

/**
 * Основной класс с логикой телеграм-бота.
 * Этот класс расширяет {@link UpdatesListener}
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot telegramBot;

    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

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

        updates.forEach(update -> {
            logger.info("Запрос от пользователя: {}", update);
            String message = update.message().text();
            long chatId = update.message().chat().id();
            String name = update.message().chat().firstName();
            long userId = update.message().from().id();
//            if (update.message().photo().){
//
//            }
            switch (message) {
                case START:
                    sendMessageKeyboard(chatId, "Здравствуйте " + name + "! " + WELCOME_MESSAGE);
                    break;
                default:
                    SendMessage errorMessage = new SendMessage(update.message().chat().id(), ERROR_MESSAGE);
                    telegramBot.execute(errorMessage);
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * Метод, который выводит сообщения пользователю и кнопки команд
     *
     * @param chatId  Идентификатор чата
     * @param message Сообщение пользователя
     */
    private void sendMessageKeyboard(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(new ReplyKeyboardMarkup(new String[][]{
                {"Узнать информацию о приюте", "Как взять собаку из приюта"},
                {"Прислать отчет о питомце", "Позвать волонтера"}
        }).resizeKeyboard(true));

        SendResponse sendResponse = telegramBot.execute(sendMessage);
    }
}
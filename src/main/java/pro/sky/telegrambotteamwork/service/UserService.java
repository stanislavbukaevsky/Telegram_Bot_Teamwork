package pro.sky.telegrambotteamwork.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotteamwork.model.User;
import pro.sky.telegrambotteamwork.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Серивис-класс для всех пользователей ботом
 */
@Service
@AllArgsConstructor
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final TelegramBot telegramBot;
    private final UserRepository userRepository;

    /**
     * Метод, который сохраняет пользователя в базу данных
     *
     * @param update входящее обновление
     */
    public void saveUser(Update update) {
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

}

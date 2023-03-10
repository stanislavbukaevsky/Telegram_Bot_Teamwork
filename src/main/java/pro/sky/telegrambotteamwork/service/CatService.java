package pro.sky.telegrambotteamwork.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotteamwork.model.Cat;
import pro.sky.telegrambotteamwork.repository.CatRepository;

import java.util.List;

/**
 * Сервис-класс для манипуляций с питомцем - кошкой
 */
@Service
@AllArgsConstructor
public class CatService {
    private final Logger logger = LoggerFactory.getLogger(CatService.class);
    private final CatRepository catRepository;

    /**
     * Метод добавления кошки в базу данных
     *
     * @param cat сущность кошки
     * @return Возвращает сохраненную в базу данных кошку
     */
    public Cat addCat(Cat cat) {
        logger.info("Вызов метода добавление кошки: {}", cat);
        return catRepository.save(cat);
    }

    /**
     * Метод редактирования кошки в базе данных
     *
     * @param cat сущность кошки
     * @return Возвращает отредактированную в базе данных кошку
     */
    public Cat updateCat(Cat cat) {
        logger.info("Вызван метод редактирования кошки: {}", cat);
        return catRepository.save(cat);
    }

    /**
     * Метод поиска кошки в базе данных
     *
     * @return Возвращает список всех найденных кошек
     */
    public List<Cat> findCat() {
        logger.info("Вызван метод поиска всех кошек");
        return catRepository.findAll();
    }

    /**
     * Метод удаления кошки из базы данных
     *
     * @param id идентификатор кошки
     */
    public void deleteCat(Long id) {
        logger.info("Вызван метод удаления кошки по id: {}", id);
        catRepository.deleteById(id);
    }
}

package pro.sky.telegrambotteamwork.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotteamwork.model.Pet;
import pro.sky.telegrambotteamwork.repository.PetRepository;

@Service
@AllArgsConstructor
public class PetService {
    private final Logger logger = LoggerFactory.getLogger(PetService.class);
    private final PetRepository petRepository;

    public Pet addPet(Pet pet) {
        logger.info("Вызов метода добавление питомца: {}", pet);
        return petRepository.save(pet);
    }

    public Pet updatePet(Pet pet) {
        logger.info("Вызван метод редактирования питомца: {}", pet);
        return petRepository.save(pet);
    }

    public Pet findPet(Long id) {
        logger.info("Вызван метод поиска всех питомцев");
        Pet pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new NullPointerException();
        }
        return pet;
    }
}

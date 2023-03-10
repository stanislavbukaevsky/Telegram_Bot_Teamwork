package pro.sky.telegrambotteamwork.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotteamwork.model.Pet;
import pro.sky.telegrambotteamwork.repository.PetRepository;

import java.util.Collection;

@Service
@AllArgsConstructor
public class PetService {
    private final Logger logger = LoggerFactory.getLogger(PetService.class);
    private final PetRepository petRepository;

    public Pet addNew(Pet pet) {
        logger.info("Was invoked method for add new pet");
        return petRepository.save(pet);
    }

    public Pet updatePet(Pet pet) {
        logger.info("Was invoked method for update student");
        return petRepository.save(pet);
    }

    public Collection<Pet> findAll() {
        return petRepository.findAll();
    }
}

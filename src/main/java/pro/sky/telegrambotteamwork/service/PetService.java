package pro.sky.telegrambotteamwork.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotteamwork.exception.NoEntityException;
import pro.sky.telegrambotteamwork.model.Pet;
import pro.sky.telegrambotteamwork.repository.PetRepository;

import java.util.Collection;

@Service
public class PetService {
    private final Logger logger = LoggerFactory.getLogger(PetService.class);
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet addNew(Pet pet) {
        logger.info("Was invoked method for add new pet");
        return petRepository.save(pet);
    }

    public void updatePet(Long id, Pet pet) {
        logger.info("Was invoked method for update pet");
        Pet updatedPet = findPetById(id);
        updatedPet.setPet_name(pet.getPet_name());
        updatedPet.setBreed(pet.getBreed());
        updatedPet.setDescription(pet.getDescription());
        updatedPet.setYearOfBirth(pet.getYearOfBirth());
        petRepository.save(updatedPet);
    }

    public Pet findPetById(Long id) {
        logger.info("Was invoked method for find pet by id");
        return petRepository.findById(id).orElseThrow(() -> new NoEntityException("Животное с id" + id + " не найдено"));
    }

    public Collection<Pet> findAll() {
        logger.info("Was invoked method for find all pets");
        return petRepository.findAll();
    }

    public void deletePet(Long id) {

    }

}

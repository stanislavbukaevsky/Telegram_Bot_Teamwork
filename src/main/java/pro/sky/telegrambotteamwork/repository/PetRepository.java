package pro.sky.telegrambotteamwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambotteamwork.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}

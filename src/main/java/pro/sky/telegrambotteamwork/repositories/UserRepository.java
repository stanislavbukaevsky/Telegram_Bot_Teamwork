package pro.sky.telegrambotteamwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambotteamwork.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

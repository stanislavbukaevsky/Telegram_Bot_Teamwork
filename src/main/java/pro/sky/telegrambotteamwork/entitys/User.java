package pro.sky.telegrambotteamwork.entitys;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "User")

/**
 * Класс с сущностью пользователя ботом
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "phone")
    private String phone;

}
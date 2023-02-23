package pro.sky.telegrambotteamwork.model;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor

@Entity
public class Dog {

    @Id
    @GeneratedValue
    private Long id;
    private String breed; // порода
    private String name;  // имя
    private int yearOfBirth; // дата рождения
    private String description; // описание

}
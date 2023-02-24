package pro.sky.telegrambotteamwork.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.File;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "Pet")
/**
 * Класс с сущностью домашнего питомца
 */
@Entity
public class Pet {

    @Id
    @GeneratedValue
    private Long id;
    private String breed; // порода
    private String name;  // имя
    private int yearOfBirth; // дата рождения
    private String description; // описание
    private File photo;
}
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
    @Column(name = "id")
    private Long id;
    @Column(name = "breed")
    private String breed;
    @Column(name = "name")
    private String name;
    @Column(name = "yearOfBirth")
    private int yearOfBirth;
    @Column(name = "description")
    private String description;
    @Column(name = "photo")
    private File photo;
}
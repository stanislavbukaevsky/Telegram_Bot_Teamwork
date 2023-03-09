package pro.sky.telegrambotteamwork.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Класс с сущностью питомца - собаки
 */
@Data
@Table(name = "dogs")
@Entity
public class Dog {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "dog_name")
    private String dogName;
    @Column(name = "breed")
    private String breed;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    @Column(name = "description")
    private String description;
}

package pro.sky.telegrambotteamwork.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Класс с сущностью питомца - кошки
 */
@Data
@Table(name = "cats")
@Entity
public class Cat {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "cat_name")
    private String catName;
    @Column(name = "breed")
    private String breed;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    @Column(name = "description")
    private String description;
}

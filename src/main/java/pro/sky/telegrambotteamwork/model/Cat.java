package pro.sky.telegrambotteamwork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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

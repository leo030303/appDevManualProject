package com.example.app_labs.entities;


import jakarta.persistence.*;
import lombok.*;
        import org.springframework.context.annotation.Bean;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "pets")
public class Pet {
    private String name;
    private String animalType;
    private String breed;
    private int age;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Pet(String name, String animalType, String breed, int age) {
        this.name = name;
        this.animalType = animalType;
        this.breed = breed;
        this.age = age;
    }
}
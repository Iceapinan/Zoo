package com.iceapinan.zoo.model;

import javax.persistence.*;
import java.util.Random;

@Entity
public class Cage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Animal animal;

    @Column(nullable = false)
    private int numberOfAnimalInside;

    public Cage() {}

    public Cage(Boolean shouldRandom) {
        if (shouldRandom) {
            populateRandomAnimal();
        }
    }

    private void populateRandomAnimal() {
        Animal[] animals = {new Sheep(), new Tiger(), new Elephant(), new Horse(), new Giraffe()};
        int rand = new Random().nextInt(animals.length);
        numberOfAnimalInside = new Random().nextBoolean() ? 0 : 1;
        if (numberOfAnimalInside == 1) {
            this.animal = animals[rand];
        }
    }

    public Cage(Long id, Animal animal, int numberOfAnimalInside) {
        this.id = id;
        this.animal = animal;
        this.numberOfAnimalInside = numberOfAnimalInside;
    }

    public void putAnimalToCage(Animal animal) throws Exception {
        if (this.animal != null && this.numberOfAnimalInside != 0) {
            throw new Exception("This cage is not empty");
        }
        this.animal = animal;
        this.numberOfAnimalInside = 1;
        // Logging should be here to output this.id
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public int getNumberOfAnimalInside() {
        return numberOfAnimalInside;
    }

    public void setNumberOfAnimalInside(int numberOfAnimalInside) {
        this.numberOfAnimalInside = numberOfAnimalInside;
    }
}

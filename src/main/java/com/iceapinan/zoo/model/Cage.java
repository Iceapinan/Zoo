package com.iceapinan.zoo.model;

import com.iceapinan.zoo.service.Database;

import javax.persistence.*;
import java.util.Random;

@Entity
public class Cage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    public Animal animal;

    @Column(nullable = false)
    public int numberOfAnimalInside;

    public Cage() {}

    public Cage(Boolean shouldRandom) {
        if (shouldRandom) {
            Animal[] animals = {new Sheep(), new Tiger(), new Elephant(), new Horse(), new Giraffe()};
            int rand = new Random().nextInt(animals.length);
            numberOfAnimalInside = new Random().nextBoolean() ? 0 : 1;
            if (numberOfAnimalInside == 1)
                this.animal = animals[rand];
        }
    }

    public Cage(Long id, Animal animal, int numberOfAnimalInside) {
        this.id = id;
        this.animal = animal;
        this.numberOfAnimalInside = numberOfAnimalInside;
    }
    public void putAnimalToCage(Animal animal, Database db) throws Exception
    {
        if (this.animal != null && this.numberOfAnimalInside != 0) throw new Exception("This cage is not empty");
        this.animal = animal;
        this.numberOfAnimalInside = 1;
        System.out.println(this.id);
        db.editCage(this);
    }
}

package com.iceapinan.zoo;

import java.util.Random;

public class Cage {
    public Animal animal;
    public int numberOfAnimalInside;

    public Cage() {
        Animal[] animals = {new Sheep(), new Tiger(), new Elephant()};
        int rand = new Random().nextInt(animals.length);
        numberOfAnimalInside = new Random().nextBoolean() ? 0 : 1;
        if (numberOfAnimalInside == 1)
            this.animal = animals[rand];
    }
    public void putAnimalToCage(Animal animal) throws Exception
    {
        if (this.animal != null && this.numberOfAnimalInside != 0) throw new Exception("This cage is not empty");
        this.animal = animal;
        this.numberOfAnimalInside = 1;
    }
}

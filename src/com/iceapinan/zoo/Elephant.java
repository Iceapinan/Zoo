package com.iceapinan.zoo;

public class Elephant extends Animal {

    public Elephant() {
        this.name = "Steve";
        this.family = "Elephant (Elephantidae)";
        this.type = "Herbivore";
    }
    @Override
    public void say() {
        System.out.println("Pawoooo Pawoooo");
    }
}

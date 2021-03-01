package com.iceapinan.zoo.model;

public class Elephant extends Animal {

    public Elephant() {
        this.name = "Steve";
        this.family = "Elephant (Elephantidae)";
        this.type = "Herbivore";
    }
    @Override
    public String say() {
        return "Pawoooo Pawoooo";
    }
}

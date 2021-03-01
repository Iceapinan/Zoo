package com.iceapinan.zoo.model;

public class Sheep extends Animal {

    public Sheep() {
        this.name = "Jon";
        this.family = "Sheep (Bovidae)";
        this.type = "Herbivore";
    }
    @Override
    public String say() {
        return "Baa Baa";
    }
}

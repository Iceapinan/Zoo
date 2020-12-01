package com.iceapinan.zoo;

public class Sheep extends Animal {

    public Sheep() {
        this.name = "Jon";
        this.family = "Sheep (Bovidae)";
        this.type = "Herbivore";
    }
    @Override
    public void say() {
        System.out.println("Baa Baa");
    }
}

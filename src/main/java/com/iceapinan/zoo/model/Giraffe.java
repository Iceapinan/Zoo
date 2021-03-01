package com.iceapinan.zoo.model;

public class Giraffe extends Animal {
    public Giraffe() {
        this.name = "Rio";
        this.family = "Giraffe (Giraffidae)";
        this.type = "Herbivore";
    }
    @Override
    public String say() {
        return "WUUUUUaahhh!";
    }
}

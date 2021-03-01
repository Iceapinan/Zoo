package com.iceapinan.zoo.model;

public class Horse extends Animal {

    public Horse() {
        this.name = "Jack";
        this.family = "Horse (Equidae)";
        this.type = "Herbivore";
    }
    @Override
    public String say() {
        return "Hee Hee";
    }
}

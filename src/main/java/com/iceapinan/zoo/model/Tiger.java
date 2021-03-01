package com.iceapinan.zoo.model;

public class Tiger extends Animal{

    public Tiger() {
        this.name = "Tim";
        this.family = "Tiger (Felidae)";
        this.type = "Carnivore";
    }
    @Override
    public String say() {
        return "Grrr* Raaa*";
    }
}

package com.iceapinan.zoo;

public class Tiger extends Animal{

    public Tiger() {
        this.name = "Tim";
        this.family = "Tiger (Felidae)";
        this.type = "Carnivore";
    }
    @Override
    public void say() {
        System.out.println("Grrr* Raaa*");
    }
}

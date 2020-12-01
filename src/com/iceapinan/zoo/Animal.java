package com.iceapinan.zoo;

public abstract class Animal {
    public String name;
    public String family;
    public String type; // Herbivore or Carnivore

    public void getInfo() {
        System.out.println("Name: " + name);
        System.out.println("Family: " + family);
        System.out.println("Type: " + type);
    }
    public abstract void say();
}

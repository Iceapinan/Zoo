package com.iceapinan.zoo.controller;

import com.iceapinan.zoo.model.Cage;
import com.iceapinan.zoo.model.Zoo;
import com.iceapinan.zoo.service.Database;

import java.util.*;
import java.util.stream.Collectors;
public class ZooController {

    public Zoo zoo;

    // Constructor for random zoo
    public ZooController(){}

    public void generateZoo (Database db) {
        int min = 2;
        int max = 5;
        int numberOfCages = new Random().nextInt((max - min) + 1) + min;
        List<Cage> newCages = new ArrayList<>();
        for (int i = 0; i < numberOfCages; i++) {
            newCages.add(new Cage(true));
        }
        persist(newCages,db);
        zoo = new Zoo(newCages, newCages.size());
    }

    public void recreateZoo (Database db) {
       db.deleteAllCages();
       generateZoo(db);
    }

    void persist(List<Cage> cages, Database db) {
        for (Cage cage: cages) {
            db.updateCage(cage);
        }
    }

    public Integer countCarnivores() {
        return zoo.cages
                .stream()
                .filter((cage) -> cage.animal != null && cage.animal.type.equals("Carnivore"))
                .mapToInt(i -> 1)
                .sum();
    }

    public Integer countHerbivores() {
        return zoo.cages
                .stream()
                .filter((cage) -> cage.animal != null && cage.animal.type.equals("Herbivore"))
                .mapToInt(i -> 1)
                .sum();
    }

    public Zoo getZoo() {
        return zoo;
    }


    public List<Cage> getCages() {
        return zoo.cages;
    }


    public List<String> getCagesToDisplay() {
        return zoo.cages
                .stream()
                .map((cage) -> (cage.animal != null) ? String.valueOf(cage.animal.name) : "Empty!")
                .collect(Collectors.toList());
    }

    public int getNumberOfCages() {
        return zoo.numberOfCages;
    }

    public HashSet<String> getUnique() {
        HashSet<String> uniqueAnimalSet = new HashSet<>();
        for (Cage cage : zoo.cages) {
            if (cage.animal != null)
                uniqueAnimalSet.add(cage.animal.family);
        }
        System.out.println(uniqueAnimalSet);
        return uniqueAnimalSet;
    }

    public ArrayList<String> walk() {
        ArrayList<String> walkList = new ArrayList<>();

        walkList.add("Welcome to our zoo!");
        walkList.add("There are " + zoo.cages.size() + " cages in our zoo");
        walkList.add("---------------");
        ListIterator<Cage> it = zoo.cages.listIterator();
        while (it.hasNext()) {
            try {
                walkList.add((it.nextIndex() + 1) + ".");
                Cage cage = it.next();
                walkList.addAll(cage.animal.getInfo());
                walkList.add(cage.animal.say());
                walkList.add("---------------");
            } catch (NullPointerException e) {
                walkList.add("This cage is empty.");
                walkList.add("---------------");
            }
        }

        return walkList;
    }

    public void buyCage(Database db) {
        Cage newCage = new Cage(false);
        zoo.cages.add(newCage);
        db.updateCage(newCage);
        System.out.println("Successfully added a new cage!");
    }


    public void setZoo(Zoo readObject) {
        this.zoo = readObject;
    }
}

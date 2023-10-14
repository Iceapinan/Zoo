package com.iceapinan.zoo.controller;

import com.iceapinan.zoo.model.Cage;
import com.iceapinan.zoo.model.Zoo;
import com.iceapinan.zoo.service.Database;

import java.util.*;
import java.util.stream.Collectors;

public class ZooController {
    private Zoo zoo;

    public ZooController(Database db) {
        generateZoo(db);
    }

    public void generateZoo(Database db) {
        List<Cage> newCages = generateCages();
        persistCages(newCages, db);
        zoo = new Zoo(newCages, newCages.size());
    }

    public void recreateZoo(Database db) {
        db.deleteAllCages();
        generateZoo(db);
    }

    private void persistCages(List<Cage> cages, Database db) {
        cages.forEach(db::updateCage);
    }

    private List<Cage> generateCages() {
        int min = 2, max = 5;
        int numberOfCages = new Random().nextInt((max - min) + 1) + min;
        List<Cage> newCages = new ArrayList<>();
        for (int i = 0; i < numberOfCages; i++) {
            newCages.add(new Cage(true));
        }
        return newCages;
    }

    private Integer countAnimalsByType(String type) {
        return zoo.cages
                .stream()
                .filter(cage -> Optional.ofNullable(cage.animal).map(a -> a.type.equals(type)).orElse(false))
                .mapToInt(i -> 1)
                .sum();
    }

    public Integer countCarnivores() {
        return countAnimalsByType("Carnivore");
    }

    public Integer countHerbivores() {
        return countAnimalsByType("Herbivore");
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
                .map(cage -> Optional.ofNullable(cage.animal).map(a -> a.name).orElse("Empty!"))
                .collect(Collectors.toList());
    }

    public int getNumberOfCages() {
        return zoo.numberOfCages;
    }

    public Set<String> getUniqueAnimalFamilies() {
        return zoo.cages
                .stream()
                .filter(cage -> cage.animal != null)
                .map(cage -> cage.animal.family)
                .collect(Collectors.toSet());
    }

    public List<String> walk() {
        List<String> walkList = new ArrayList<>();
        walkList.add("Welcome to our zoo!");
        walkList.add("There are " + zoo.cages.size() + " cages in our zoo");
        walkList.add("---------------");

        for (ListIterator<Cage> it = zoo.cages.listIterator(); it.hasNext(); ) {
            walkList.add((it.nextIndex() + 1) + ".");
            Cage cage = it.next();
            Optional.ofNullable(cage.animal).ifPresentOrElse(
                    animal -> {
                        walkList.addAll(animal.getInfo());
                        walkList.add(animal.say());
                    },
                    () -> walkList.add("This cage is empty.")
            );
            walkList.add("---------------");
        }
        return walkList;
    }

    public void buyCage(Database db) {
        Cage newCage = new Cage(false);
        zoo.cages.add(newCage);
        db.updateCage(newCage);
        // Logging should be here: Successfully added a new cage
    }

    public void setZoo(Zoo readObject) {
        this.zoo = readObject;
    }
}

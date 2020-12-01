package com.iceapinan.zoo;

import java.util.*;

public class Zoo {

    public ArrayList<Cage> cages;
    public int numberOfCages;

    // God only!
    public Zoo(int numberOfCages) {
        this.numberOfCages = numberOfCages;
        this.cages = new ArrayList<>(numberOfCages);
        for (int i = 0; i < numberOfCages; i++) {
            cages.add(new Cage());
        }
    }

    public void getUnique() {
        HashSet<String> uniqueAnimalSet = new HashSet<>();
        for (Cage cage: cages) {
            if (cage.animal != null)
                uniqueAnimalSet.add(cage.animal.family);
        }
        System.out.println(uniqueAnimalSet);
    }

    public void walk() {
        System.out.println("Welcome to our zoo!");
        System.out.println("There are " + cages.size() + " cages in our zoo");
        System.out.println("---------------");
        ListIterator<Cage> it = cages.listIterator();
        while(it.hasNext()) {
            try {
                System.out.println((it.nextIndex() + 1) + ".");
                Cage cage = it.next();
                cage.animal.getInfo();
                cage.animal.say();
                System.out.println("---------------");
            } catch(NullPointerException e) {
                System.out.println("This cage is empty.");
                System.out.println("---------------");
            }
        }
    }

    public void buyCage() throws Exception {
        Cage newCage = new Cage();
        cages.add(newCage);
        System.out.println("Successfully added a new cage!");
        if (newCage.numberOfAnimalInside == 0) {
            System.out.println("This cage you just brought is empty, please add an animal");
            System.out.println("\n1. Sheep\n" +
                    "2. Tiger\n" +
                    "3. Buy a cage\n");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case (1) -> newCage.putAnimalToCage(new Sheep());
                case (2) -> newCage.putAnimalToCage(new Tiger());
                case (3) -> newCage.putAnimalToCage(new Elephant());
                default -> System.out.println("Invalid choice");
            }
            System.out.println("New animal added to the cage!");
        }
    }

}

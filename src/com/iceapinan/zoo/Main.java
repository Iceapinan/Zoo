package com.iceapinan.zoo;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // In the beginning God created the zoo and determined the number of cages (which is arbitrary)
        int min = 2;
        int max = 5;
        int numberOfCages = new Random().nextInt((max - min) + 1) + min;
        Zoo zoo = new Zoo(numberOfCages);

        Scanner scanner = new Scanner(System.in);
        int currentPosition;
        do {
            System.out.println("\n1. Entrance\n" +
                    "2. Get unique list of animals\n" +
                    "3. Buy a cage\n" +
                    "9. Exit \n");
            currentPosition = scanner.nextInt();
            switch (currentPosition) {
                case (1) -> zoo.walk();
                case (2) -> zoo.getUnique();
                case (3) -> zoo.buyCage();
            }

        } while(currentPosition != 9);
    }
}

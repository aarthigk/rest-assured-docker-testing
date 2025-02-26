package utils;

import java.util.Random;

public class TestDataGenerator {

    // Method to generate a random ID for the pet
    public static int generateRandomId() {
        Random random = new Random();
        return random.nextInt(10000);  // Random number between 0 and 9999
    }

    // Method to generate random pet name
    public static String generateRandomName() {
        String[] names = {"doggie", "kitty", "bunny", "hamster", "parrot"};
        Random random = new Random();
        return names[random.nextInt(names.length)];  // Random name from array
    }

    // Method to generate random pet status
    public static String generateRandomStatus() {
        String[] statuses = {"available", "pending", "sold"};
        Random random = new Random();
        return statuses[random.nextInt(statuses.length)];  // Random status from array
    }

    // Generate the full request payload for creating a pet
    public static String generatePetPayload() {
        int id = generateRandomId();
        String name = generateRandomName();
        String status = generateRandomStatus();

        return "{ \"id\": " + id + ", \"name\": \"" + name + "\", \"status\": \"" + status + "\" }";
    }

//    // Example of generating full pet data for use in tests
//    public static void main(String[] args) {
//        // Generating and printing a random pet payload
//        String petPayload = generatePetPayload();
//        System.out.println(petPayload);
//    }
}

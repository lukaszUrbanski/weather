package com.sda.weather.client;

import com.sda.weather.application.LocationController;

import java.util.Scanner;

public class Client {

    private final LocationController locationController = new LocationController();

    public void runClientInterface() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Application is running. ");

            System.out.println("1. Add location.");
            System.out.println("2. Show available locations");
            System.out.println("3. Show weather information");
            System.out.println("0. Close application.");
            int response = scanner.nextInt();

            switch (response) {
                case 1:
                    addNewLocation();
                    break;
                case 2:
                    showLocation();
                    break;
                case 3:
                    showWeatherInformation();
                    break;
                case 0:
                    return;
            }
            
        }
    }

    private void showWeatherInformation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter city to check weather ");
        String cityName = scanner.nextLine();

        findLocation(cityName);

    }

    private void findLocation(final String cityName) {
        locationController.findLocation(cityName);
    }

    private void showLocation() {
        String response = locationController.showAllLocations();
        System.out.println(response);
    }

    private void addNewLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String name = scanner.nextLine();
        System.out.print("Enter latitude: ");
        double latitude = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter longitude: ");
        double longitude = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter region: ");
        String region = scanner.nextLine();
        System.out.print("Country: ");
        String country = scanner.nextLine();

        String response = locationController.addLocation(name, latitude, longitude, region, country);
        System.out.println("New location has added: " + response);

    }
}

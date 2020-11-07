package com.sda.weather.client;

import com.sda.weather.application.location.LocationController;
import com.sda.weather.application.weather.WeatherController;

import java.util.Scanner;

public class
Client {

    private final LocationController locationController = new LocationController();
    private final WeatherController weatherController = new WeatherController();

    public void runClientInterface() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Application is running. ");

            System.out.println("1. Add location.");
            System.out.println("2. Show available locations");
            System.out.println("3. Show weather information by city name");
            System.out.println("4. Show weather information by coordinate");
            System.out.println("0. Close application.");
            int response = scanner.nextInt();

            switch (response) {
                case 1:
                    addNewLocation();
                    break;
                case 2:
                    showAllLocations();
                    break;
                case 3:
                    showWeatherInformation();
                    break;
                case 4:
                    showWeatherInformationByCoordinates();
                    break;
                case 0:
                    return;
            }
            
        }
    }

    private void showWeatherInformationByCoordinates() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter location latitude: ");
        double latitude = scanner.nextDouble();

        System.out.print("Enter location longitude: ");
        double longitude = scanner.nextDouble();

        System.out.print("Enter date: ");
        String date = scanner.nextLine();

        System.out.println(weatherController.showWeatherInformation(latitude, longitude, date));
    }

    private void showWeatherInformation() {
        showAllLocations();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter city to check weather ");
        String cityName = scanner.nextLine();

        System.out.print("Enter date of weather(yyyy-mm-dd)");
        String date = scanner.nextLine();

        System.out.println(weatherController.showWeatherInformation(cityName, date));
    }

    private void showAllLocations() {
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

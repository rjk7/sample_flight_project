package com.example;

import com.example.model.Flight;
import com.example.model.Reservation;
import com.example.service.FlightService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final FlightService flightService = new FlightService();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== FLIGHT RESERVATION SYSTEM ===");
            System.out.println("1. Search Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View My Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> searchFlights();
                case 2 -> bookFlight();
                case 3 -> viewReservations();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void searchFlights() {
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter date (yyyy-MM-dd HH:mm): ");
        String dateInput = scanner.nextLine();
        LocalDateTime date = LocalDateTime.parse(dateInput, dtf);

        List<Flight> results = flightService.searchFlights(destination, date);

        if (results.isEmpty()) {
            System.out.println("No flights found.");
        } else {
            results.forEach(System.out::println);
        }
    }

    private static void bookFlight() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine();

        Flight flight = flightService.getAllFlights().stream()
                .filter(f -> f.getFlightNumber().equalsIgnoreCase(flightNumber))
                .findFirst()
                .orElse(null);

        if (flight == null) {
            System.out.println("Flight not found!");
            return;
        }

        System.out.print("Enter number of seats to book: ");
        int seats = Integer.parseInt(scanner.nextLine());

        Reservation reservation = flightService.bookFlight(name, flight, seats);

        if (reservation != null) {
            System.out.println("Booking successful: " + reservation);
        }
    }

    private static void viewReservations() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        List<Reservation> reservations = flightService.getReservationsByCustomer(name);

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            reservations.forEach(System.out::println);
        }
    }
}

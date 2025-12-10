package com.example.service;

import com.example.model.Flight;
import com.example.model.Reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

    private final List<Flight> flights = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public FlightService() {
        seedData();
    }


    private void seedData() {
        flights.add(new Flight("A101", "New York", LocalDateTime.of(2025, 1, 20, 10, 30), 50));
        flights.add(new Flight("B202", "Chicago", LocalDateTime.of(2025, 1, 20, 12, 45), 20));
        flights.add(new Flight("C303", "New York", LocalDateTime.of(2025, 1, 20, 18, 00), 10));
        flights.add(new Flight("D404", "Los Angeles", LocalDateTime.of(2025, 1, 21, 9, 15), 35));
    }

    public List<Flight> searchFlights(String destination, LocalDateTime date) {
        LocalDate targetDate = date.toLocalDate();

        return flights.stream()
                .filter(f -> f.getDestination().equalsIgnoreCase(destination)
                        && f.getDepartureTime().toLocalDate().isEqual(targetDate)
                        && f.getAvailableSeats() > 0)
                .collect(Collectors.toList());
    }

    public Reservation bookFlight(String customerName, Flight flight, int seats) {
        if (flight.getAvailableSeats() < seats) {
            System.out.println("Not enough seats available!");
            return null;
        }
        if (seats <= 0){
            return null;
        }
        flight.setAvailableSeats(flight.getAvailableSeats() - seats);
        Reservation reservation = new Reservation(customerName, flight, seats);
        reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getReservationsByCustomer(String customerName) {
        return reservations.stream()
                .filter(r -> r.getCustomerName().equalsIgnoreCase(customerName))
                .collect(Collectors.toList());
    }

    public List<Flight> getAllFlights() {
        return flights;
    }
}

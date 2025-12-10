package com.example.service;

import com.example.model.Flight;
import com.example.model.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class FlightServiceTest {

    @Test
    void testSearchFlightsReturnsCorrectResults() {
        FlightService service = new FlightService();

        List<Flight> flights = service.searchFlights(
                "New York",
                LocalDateTime.of(2025, 1, 20, 9, 0)
        );

        Assertions.assertFalse(flights.isEmpty());
    }

    @Test
    void testSuccessfulBooking() {
        FlightService service = new FlightService();
        Flight flight = service.getAllFlights().get(0);

        int initialSeats = flight.getAvailableSeats();

        Reservation reservation = service.bookFlight("Ajith", flight, 5);

        Assertions.assertNotNull(reservation);
        Assertions.assertEquals(initialSeats - 5, flight.getAvailableSeats());
    }

    @Test
    void testBookingFailsWhenSeatsExceedAvailability() {
        FlightService service = new FlightService();
        Flight flight = service.getAllFlights().get(2); // e.g., has 10 seats

        int initialSeats = flight.getAvailableSeats();

        Reservation reservation = service.bookFlight("Ajith", flight, 50); // Try to overbook

        // Booking should fail
        Assertions.assertNull(reservation, "Reservation should fail when overbooking");

        // Seats must NOT decrease
        Assertions.assertEquals(initialSeats, flight.getAvailableSeats(),
                "Seats must remain unchanged when booking fails");
    }

    @Test
    void testBookingFailsForInvalidSeatCount() {
        FlightService service = new FlightService();
        Flight flight = service.getAllFlights().get(0);

        Reservation res = service.bookFlight("Ajith", flight, -3);
        Assertions.assertNull(res, "Booking negative seats should fail");
    }


    @Test
    void testSearchReturnsEmptyListWhenNoFlightsFound() {
        FlightService service = new FlightService();

        List<Flight> flights = service.searchFlights(
                "Miami",
                LocalDateTime.of(2025, 1, 20, 12, 0)
        );

        Assertions.assertTrue(flights.isEmpty());
    }
}

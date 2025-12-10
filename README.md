# Flight Reservation System (Java Console Application)

## Overview
This project simulates a simple Flight Reservation System similar to real-world airline booking platforms.  
Users can:

- Search for flights by destination and date
- Book flights
- View their reservations

The system is built using **Java**, with **JUnit** used for unit testing, and uses **in-memory lists** for data storage.

---

## Features

### ✔ Flight Management
- Stores flight number, destination, date-time, and available seats.

### ✔ Reservations
- Tracks customer name, flight booked, and number of seats reserved.

### ✔ FlightService
- **searchFlights()** → filters flights by destination & date
- **bookFlight()** → ensures no overbooking; prevents availableSeats < 0
- Retrieves reservations per customer

### ✔ Console Interface
Users can:
- Search flights
- Select and book flights
- View all reservations

---

## Project Structure


# Java-Based Airline Reservation & Management System

An automated flight booking and management solution developed as a final project for the **CPCS203: Programming-II** course at **King Abdulaziz University**. This system demonstrates a strong command of **Object-Oriented Programming (OOP)**, **File I/O operations**, and **Data Management** in Java.

---

## 🚀 Key Functionalities
* **Automated Booking**: Manages passenger registrations and flight scheduling through structured file processing.
* **Dynamic Seat Allocation**: Implements a 2D-array seat map system to track, verify, and reserve specific seats (Economy, Business, and First Class).
* **Smart Invoicing Engine**: A robust pricing logic that calculates total costs based on seat class, VAT (15%), and specific regional discounts (e.g., JED Hajj Season discounts).
* **Sequential Reservation Tracking**: Generates unique, sequential confirmation numbers starting from 100 for every successful booking.

---

## 🏗️ Architecture & Class Structure
The system follows a clean OOP design with four primary modules:
* **BookingSystem**: The core controller that manages input/output streams and command execution.
* **Flight**: Handles flight-specific data and maintains the integrity of the seat map.
* **Passenger**: A dedicated entity for managing passenger identities and passport details.
* **Ticket**: The bridge between flights and passengers, calculating final prices and storing booking metadata.

---

## 📊 File Processing (I/O)
The application operates by parsing external text files and logging results:
* **Inputs**: Reads initial data from `flight_passenger.txt` and operational commands from `inputCommands.txt`.
* **Outputs**: Generates a comprehensive `output.txt` detailing all successful transactions, errors, and invoices.

---

## 💰 Pricing
* **Base Fare**: 2000 SR.
* **Class Scaling**: 
    * **Business**: +200% of base.
    * **First Class**: +400% of base.
* **Special Conditions**: Applies a 15% VAT and a 20% seasonal discount for flights arriving in Jeddah (JED).

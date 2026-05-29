package com.pratik;

import java.util.*;

public class SmartParkingManager {

    static class Vehicle {

        private String vehicleNumber;
        private String vehicleType;

        public Vehicle(
                String vehicleNumber,
                String vehicleType) {

            this.vehicleNumber =
                    vehicleNumber;

            this.vehicleType =
                    vehicleType;
        }

        public String getVehicleNumber() {
            return vehicleNumber;
        }

        public String getVehicleType() {
            return vehicleType;
        }
    }

    static class ParkingLot {

        private int capacity;

        private Map<Integer, Vehicle>
                occupiedSlots;

        public ParkingLot(int capacity) {

            this.capacity = capacity;

            occupiedSlots =
                    new HashMap<>();
        }

        // Park vehicle
        public boolean parkVehicle(
                Vehicle vehicle) {

            if (occupiedSlots.size()
                    >= capacity) {

                System.out.println(
                        "Parking Full!");
                return false;
            }

            for (int slot = 1;
                 slot <= capacity;
                 slot++) {

                if (!occupiedSlots
                        .containsKey(slot)) {

                    occupiedSlots.put(
                            slot,
                            vehicle);

                    System.out.println(
                            "Vehicle parked at slot: "
                                    + slot);

                    return true;
                }
            }

            return false;
        }

        // Remove vehicle
        public void removeVehicle(
                int slot) {

            if (occupiedSlots
                    .containsKey(slot)) {

                occupiedSlots.remove(
                        slot);

                System.out.println(
                        "Vehicle removed from slot: "
                                + slot);

            } else {

                System.out.println(
                        "Slot already empty!");
            }
        }

        // Show status
        public void showParkingStatus() {

            System.out.println(
                    "\nOccupied Slots:");

            for (Map.Entry<Integer,
                    Vehicle> entry
                    : occupiedSlots.entrySet()) {

                Vehicle vehicle =
                        entry.getValue();

                System.out.println(
                        "Slot "
                                + entry.getKey()
                                + " -> "
                                + vehicle
                                .getVehicleNumber()
                                + " ("
                                + vehicle
                                .getVehicleType()
                                + ")");
            }

            System.out.println(
                    "Available Slots: "
                            + (capacity
                            - occupiedSlots.size()));
        }
    }

    public static void main(String[] args) {

        ParkingLot parkingLot =
                new ParkingLot(5);

        Vehicle car1 =
                new Vehicle(
                        "MH12AB1234",
                        "Car");

        Vehicle bike1 =
                new Vehicle(
                        "MH14XY5678",
                        "Bike");

        parkingLot.parkVehicle(car1);
        parkingLot.parkVehicle(bike1);

        parkingLot.showParkingStatus();

        parkingLot.removeVehicle(1);

        parkingLot.showParkingStatus();
    }
}
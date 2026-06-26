package com.pratik;

import java.util.*;

public class RideSharingSimulator {

    static class Driver {
        private String driverId;
        private String name;
        private double location;
        private boolean available;

        public Driver(String driverId, String name, double location) {
            this.driverId = driverId;
            this.name = name;
            this.location = location;
            this.available = true;
        }

        public boolean isAvailable() {
            return available;
        }

        public void assignRide() {
            available = false;
        }

        public double getLocation() {
            return location;
        }

        @Override
        public String toString() {
            return driverId + " - " + name;
        }
    }

    static class Rider {
        private String riderId;
        private String name;

        public Rider(String riderId, String name) {
            this.riderId = riderId;
            this.name = name;
        }
    }

    static class RideService {
        private List<Driver> drivers = new ArrayList<>();

        public void registerDriver(Driver driver) {
            drivers.add(driver);
        }

        public Driver findNearestDriver(double riderLocation) {
            Driver nearest = null;
            double minDistance = Double.MAX_VALUE;

            for (Driver driver : drivers) {
                if (driver.isAvailable()) {
                    double distance =
                            Math.abs(driver.getLocation() - riderLocation);

                    if (distance < minDistance) {
                        minDistance = distance;
                        nearest = driver;
                    }
                }
            }

            return nearest;
        }

        public void bookRide(Rider rider, double riderLocation) {
            Driver driver = findNearestDriver(riderLocation);

            if (driver == null) {
                System.out.println("No driver available");
                return;
            }

            driver.assignRide();

            double fare =
                    50 + Math.abs(driver.getLocation() - riderLocation) * 10;

            System.out.println("Ride booked!");
            System.out.println("Driver: " + driver);
            System.out.println("Fare: ₹" + fare);
        }
    }

    public static void main(String[] args) {

        RideService service = new RideService();

        service.registerDriver(new Driver("D1", "Raj", 10));
        service.registerDriver(new Driver("D2", "Amit", 25));
        service.registerDriver(new Driver("D3", "Ravi", 18));

        Rider rider = new Rider("R1", "Pratik");

        service.bookRide(rider, 20);
    }
}
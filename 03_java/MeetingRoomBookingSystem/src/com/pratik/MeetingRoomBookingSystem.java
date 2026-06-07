package com.pratik;

import java.util.*;

public class MeetingRoomBookingSystem {

    static class Booking {

        private String roomName;
        private String startTime;
        private String endTime;

        public Booking(String roomName,
                       String startTime,
                       String endTime) {

            this.roomName = roomName;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        @Override
        public String toString() {

            return roomName +
                    " [" + startTime +
                    " - " + endTime + "]";
        }
    }

    static class BookingManager {

        private final Map<String,
                List<Booking>> bookings =
                new HashMap<>();

        public boolean bookRoom(
                String roomName,
                String startTime,
                String endTime) {

            bookings.putIfAbsent(
                    roomName,
                    new ArrayList<>());

            List<Booking> roomBookings =
                    bookings.get(roomName);

            for (Booking booking
                    : roomBookings) {

                if (!(endTime.compareTo(
                        booking.getStartTime()) <= 0
                        ||
                        startTime.compareTo(
                                booking.getEndTime()) >= 0)) {

                    System.out.println(
                            "Time slot already booked!");

                    return false;
                }
            }

            roomBookings.add(
                    new Booking(
                            roomName,
                            startTime,
                            endTime));

            System.out.println(
                    "Booking Successful");

            return true;
        }

        public void showBookings(
                String roomName) {

            System.out.println(
                    "\nBookings for "
                            + roomName);

            List<Booking> roomBookings =
                    bookings.getOrDefault(
                            roomName,
                            new ArrayList<>());

            for (Booking booking
                    : roomBookings) {

                System.out.println(booking);
            }
        }
    }

    public static void main(String[] args) {

        BookingManager manager =
                new BookingManager();

        manager.bookRoom(
                "Conference-A",
                "09:00",
                "10:00");

        manager.bookRoom(
                "Conference-A",
                "10:30",
                "11:30");

        manager.bookRoom(
                "Conference-A",
                "09:30",
                "10:15");

        manager.showBookings(
                "Conference-A");
    }
}
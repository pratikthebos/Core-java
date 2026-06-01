package com.pratik;

import java.time.*;
import java.util.*;

public class EmployeeAttendanceTracker {

    static class AttendanceRecord {

        private String employeeId;
        private LocalDate date;
        private LocalDateTime checkInTime;
        private LocalDateTime checkOutTime;

        public AttendanceRecord(String employeeId) {
            this.employeeId = employeeId;
            this.date = LocalDate.now();
        }

        public void checkIn() {
            checkInTime = LocalDateTime.now();
        }

        public void checkOut() {
            checkOutTime = LocalDateTime.now();
        }

        public long getWorkingHours() {

            if (checkInTime == null ||
                    checkOutTime == null) {
                return 0;
            }

            return Duration.between(
                    checkInTime,
                    checkOutTime
            ).toHours();
        }

        @Override
        public String toString() {

            return "Employee: " + employeeId +
                    ", Date: " + date +
                    ", Check-In: " + checkInTime +
                    ", Check-Out: " + checkOutTime +
                    ", Hours Worked: " + getWorkingHours();
        }
    }

    static class AttendanceManager {

        private final Map<String,
                AttendanceRecord> records =
                new HashMap<>();

        public void checkIn(String employeeId) {

            AttendanceRecord record =
                    new AttendanceRecord(employeeId);

            record.checkIn();

            records.put(employeeId, record);

            System.out.println(
                    employeeId +
                    " checked in successfully.");
        }

        public void checkOut(String employeeId) {

            AttendanceRecord record =
                    records.get(employeeId);

            if (record == null) {

                System.out.println(
                        "Employee not found.");
                return;
            }

            record.checkOut();

            System.out.println(
                    employeeId +
                    " checked out successfully.");
        }

        public void showAttendance() {

            System.out.println(
                    "\nAttendance Records:");

            for (AttendanceRecord record
                    : records.values()) {

                System.out.println(record);
            }
        }
    }

    public static void main(String[] args)
            throws Exception {

        AttendanceManager manager =
                new AttendanceManager();

        manager.checkIn("EMP101");

        Thread.sleep(2000);

        manager.checkOut("EMP101");

        manager.showAttendance();
    }
}

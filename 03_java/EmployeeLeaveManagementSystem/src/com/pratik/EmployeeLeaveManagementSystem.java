package com.pratik;

import java.util.*;

public class EmployeeLeaveManagementSystem {

    static class LeaveRequest {

        private int requestId;
        private String employeeName;
        private int leaveDays;
        private String status;

        public LeaveRequest(int requestId,
                            String employeeName,
                            int leaveDays) {

            this.requestId = requestId;
            this.employeeName = employeeName;
            this.leaveDays = leaveDays;
            this.status = "PENDING";
        }

        public int getRequestId() {
            return requestId;
        }

        public int getLeaveDays() {
            return leaveDays;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {

            return "Request ID: " + requestId +
                    ", Employee: " + employeeName +
                    ", Leave Days: " + leaveDays +
                    ", Status: " + status;
        }
    }

    static class LeaveManager {

        private final Map<Integer, LeaveRequest>
                requests = new HashMap<>();

        private final Map<String, Integer>
                leaveBalance = new HashMap<>();

        public void addEmployee(
                String employeeName,
                int totalLeaves) {

            leaveBalance.put(
                    employeeName,
                    totalLeaves);
        }

        public void applyLeave(
                LeaveRequest request) {

            requests.put(
                    request.getRequestId(),
                    request);

            System.out.println(
                    "Leave request submitted.");
        }

        public void approveLeave(
                int requestId,
                String employeeName) {

            LeaveRequest request =
                    requests.get(requestId);

            if (request == null) {
                return;
            }

            int balance =
                    leaveBalance.getOrDefault(
                            employeeName,
                            0);

            if (balance >= request.getLeaveDays()) {

                leaveBalance.put(
                        employeeName,
                        balance -
                                request.getLeaveDays());

                request.setStatus("APPROVED");

            } else {

                request.setStatus("REJECTED");
            }
        }

        public void viewRequests() {

            System.out.println(
                    "\nLeave Requests:");

            for (LeaveRequest request
                    : requests.values()) {

                System.out.println(request);
            }
        }

        public void showBalance(
                String employeeName) {

            System.out.println(
                    employeeName +
                            " Leave Balance: "
                            + leaveBalance.getOrDefault(
                            employeeName,
                            0));
        }
    }

    public static void main(String[] args) {

        LeaveManager manager =
                new LeaveManager();

        manager.addEmployee(
                "Pratik", 20);

        LeaveRequest request =
                new LeaveRequest(
                        101,
                        "Pratik",
                        5);

        manager.applyLeave(request);

        manager.approveLeave(
                101,
                "Pratik");

        manager.viewRequests();

        manager.showBalance(
                "Pratik");
    }
}
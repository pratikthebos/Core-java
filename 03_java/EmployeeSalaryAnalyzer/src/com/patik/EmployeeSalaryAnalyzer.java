package com.patik;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class EmployeeSalaryAnalyzer {

    static class Employee {

        private int id;
        private String name;
        private String department;
        private double salary;

        public Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return String.format(
                    "ID: %-4d | Name: %-15s | Department: %-12s | Salary: %.2f",
                    id, name, department, salary
            );
        }
    }

    private static final List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loadSampleEmployees();

        while (true) {

            printMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addEmployee(scanner);
                    break;

                case 2:
                    displayEmployees();
                    break;

                case 3:
                    findHighestPaidEmployee();
                    break;

                case 4:
                    calculateAverageSalary();
                    break;

                case 5:
                    sortBySalary();
                    break;

                case 6:
                    searchByDepartment(scanner);
                    break;

                case 7:
                    removeEmployee(scanner);
                    break;

                case 8:
                    System.out.println("\nApplication closed successfully.");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {

        System.out.println("\n======================================");
        System.out.println("       EMPLOYEE SALARY ANALYZER");
        System.out.println("======================================");
        System.out.println("1. Add Employee");
        System.out.println("2. Display Employees");
        System.out.println("3. Find Highest Paid Employee");
        System.out.println("4. Calculate Average Salary");
        System.out.println("5. Sort Employees by Salary");
        System.out.println("6. Search by Department");
        System.out.println("7. Remove Employee");
        System.out.println("8. Exit");
        System.out.println("======================================");
    }

    private static void loadSampleEmployees() {

        employees.add(
                new Employee(101, "Rahul", "Engineering", 85000)
        );

        employees.add(
                new Employee(102, "Priya", "HR", 65000)
        );

        employees.add(
                new Employee(103, "Amit", "Engineering", 95000)
        );

        employees.add(
                new Employee(104, "Sneha", "Finance", 78000)
        );

        employees.add(
                new Employee(105, "Vikas", "Engineering", 72000)
        );
    }

    private static void addEmployee(Scanner scanner) {

        System.out.println("\n--- Add Employee ---");

        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (findEmployeeById(id) != null) {
            System.out.println("Employee ID already exists.");
            return;
        }

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        if (salary < 0) {
            System.out.println("Salary cannot be negative.");
            return;
        }

        employees.add(
                new Employee(id, name, department, salary)
        );

        System.out.println("Employee added successfully.");
    }

    private static void displayEmployees() {

        System.out.println("\n--- Employee List ---");

        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("--------------------------------------");
        System.out.println("Total Employees: " + employees.size());
    }

    private static void findHighestPaidEmployee() {

        if (employees.isEmpty()) {
            System.out.println("No employees available.");
            return;
        }

        Employee highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);

        System.out.println("\n--- Highest Paid Employee ---");
        System.out.println(highestPaid);
    }

    private static void calculateAverageSalary() {

        if (employees.isEmpty()) {
            System.out.println("No employees available.");
            return;
        }

        double totalSalary = 0;

        for (Employee employee : employees) {
            totalSalary += employee.getSalary();
        }

        double averageSalary = totalSalary / employees.size();

        System.out.printf(
                "\nAverage Employee Salary: %.2f%n",
                averageSalary
        );
    }

    private static void sortBySalary() {

        employees.sort(
                Comparator.comparingDouble(Employee::getSalary)
                        .reversed()
        );

        System.out.println("\nEmployees sorted by salary:");

        displayEmployees();
    }

    private static void searchByDepartment(Scanner scanner) {

        System.out.print("\nEnter department: ");
        String department = scanner.nextLine();

        boolean found = false;

        System.out.println("\n--- Department Employees ---");

        for (Employee employee : employees) {

            if (employee.getDepartment()
                    .equalsIgnoreCase(department)) {

                System.out.println(employee);
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No employees found in department: " + department
            );
        }
    }

    private static void removeEmployee(Scanner scanner) {

        System.out.print("\nEnter Employee ID to remove: ");
        int id = scanner.nextInt();

        Employee employee = findEmployeeById(id);

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        employees.remove(employee);

        System.out.println(
                "Employee " + employee.getName()
                        + " removed successfully."
        );
    }

    private static Employee findEmployeeById(int id) {

        for (Employee employee : employees) {

            if (employee.getId() == id) {
                return employee;
            }
        }

        return null;
    }
}
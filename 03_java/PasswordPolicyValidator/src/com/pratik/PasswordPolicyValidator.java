package com.pratik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordPolicyValidator {

    static class ValidationResult {

        private boolean valid;
        private final List<String> errors;

        public ValidationResult() {
            this.valid = true;
            this.errors = new ArrayList<>();
        }

        public void addError(String error) {
            valid = false;
            errors.add(error);
        }

        public boolean isValid() {
            return valid;
        }

        public List<String> getErrors() {
            return errors;
        }
    }

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 32;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    validatePassword(scanner);
                    break;

                case 2:
                    testSamplePasswords();
                    break;

                case 3:
                    displayPolicy();
                    break;

                case 4:
                    System.out.println(
                            "\nPassword Policy Validator closed."
                    );
                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "\nInvalid choice."
                    );
            }
        }
    }

    private static void displayMenu() {

        System.out.println(
                "\n=============================================="
        );
        System.out.println(
                "          PASSWORD POLICY VALIDATOR"
        );
        System.out.println(
                "=============================================="
        );
        System.out.println("1. Validate Password");
        System.out.println("2. Test Sample Passwords");
        System.out.println("3. Display Password Policy");
        System.out.println("4. Exit");
        System.out.println(
                "=============================================="
        );
    }

    private static void validatePassword(
            Scanner scanner) {

        System.out.println("\n--- Password Validation ---");

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        ValidationResult result =
                validate(password);

        displayResult(result);
    }

    private static ValidationResult validate(
            String password) {

        ValidationResult result =
                new ValidationResult();

        if (password == null || password.isEmpty()) {

            result.addError(
                    "Password cannot be empty."
            );

            return result;
        }

        if (password.length() < MIN_LENGTH) {

            result.addError(
                    "Minimum length is "
                            + MIN_LENGTH
                            + " characters."
            );
        }

        if (password.length() > MAX_LENGTH) {

            result.addError(
                    "Maximum length is "
                            + MAX_LENGTH
                            + " characters."
            );
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {

            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }

        if (!hasUppercase) {

            result.addError(
                    "At least one uppercase letter is required."
            );
        }

        if (!hasLowercase) {

            result.addError(
                    "At least one lowercase letter is required."
            );
        }

        if (!hasDigit) {

            result.addError(
                    "At least one digit is required."
            );
        }

        if (!hasSpecial) {

            result.addError(
                    "At least one special character is required."
            );
        }

        if (containsWhitespace(password)) {

            result.addError(
                    "Password cannot contain whitespace."
            );
        }

        if (hasRepeatedCharacters(password)) {

            result.addError(
                    "Password cannot contain three consecutive identical characters."
            );
        }

        return result;
    }

    private static boolean containsWhitespace(
            String password) {

        for (char ch : password.toCharArray()) {

            if (Character.isWhitespace(ch)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasRepeatedCharacters(
            String password) {

        for (int i = 2; i < password.length(); i++) {

            if (password.charAt(i)
                    == password.charAt(i - 1)
                    && password.charAt(i)
                    == password.charAt(i - 2)) {

                return true;
            }
        }

        return false;
    }

    private static void displayResult(
            ValidationResult result) {

        if (result.isValid()) {

            System.out.println(
                    "\nPASSWORD VALID"
            );

            System.out.println(
                    "Password satisfies all security rules."
            );

        } else {

            System.out.println(
                    "\nPASSWORD INVALID"
            );

            System.out.println(
                    "\nProblems found:"
            );

            for (String error : result.getErrors()) {

                System.out.println(
                        "- " + error
                );
            }
        }
    }

    private static void testSamplePasswords() {

        String[] passwords = {
                "Hello@123",
                "password",
                "PASSWORD123",
                "Pratik@2026",
                "Abc111@xyz",
                "Test@123"
        };

        System.out.println(
                "\n--- Sample Password Test ---"
        );

        for (String password : passwords) {

            ValidationResult result =
                    validate(password);

            System.out.println(
                    String.format(
                            "%-20s : %s",
                            password,
                            result.isValid()
                                    ? "VALID"
                                    : "INVALID"
                    )
            );
        }
    }

    private static void displayPolicy() {

        System.out.println(
                "\n--- Password Security Policy ---"
        );

        System.out.println(
                "1. Minimum length : "
                        + MIN_LENGTH
        );

        System.out.println(
                "2. Maximum length : "
                        + MAX_LENGTH
        );

        System.out.println(
                "3. Uppercase      : Required"
        );

        System.out.println(
                "4. Lowercase      : Required"
        );

        System.out.println(
                "5. Digit           : Required"
        );

        System.out.println(
                "6. Special char    : Required"
        );

        System.out.println(
                "7. Whitespace      : Not allowed"
        );

        System.out.println(
                "8. Three repeated characters : Not allowed"
        );
    }
}
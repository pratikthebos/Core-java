package com.pratik;

public class DataTypes {

	public static void main(String[] args) {

        // 1. Primitive Data Types

        byte byteValue = 100;           // 8-bit
        short shortValue = 32000;       // 16-bit
        int intValue = 100000;          // 32-bit
        long longValue = 10000000000L;  // 64-bit

        float floatValue = 10.5f;       // 32-bit decimal
        double doubleValue = 99.99;     // 64-bit decimal

        char charValue = 'A';           // Single character
        boolean booleanValue = true;    // true / false

        // 2. Non-Primitive (Reference) Data Types

        String stringValue = "Hello Java";
        int[] arrayValue = {1, 2, 3, 4, 5};

        // Output
        System.out.println("byte: " + byteValue);
        System.out.println("short: " + shortValue);
        System.out.println("int: " + intValue);
        System.out.println("long: " + longValue);

        System.out.println("float: " + floatValue);
        System.out.println("double: " + doubleValue);

        System.out.println("char: " + charValue);
        System.out.println("boolean: " + booleanValue);

        System.out.println("String: " + stringValue);

        System.out.print("Array: ");
        for (int num : arrayValue) {
            System.out.print(num + " ");
        }
    }

}

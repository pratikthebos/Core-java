package com.pratik;



public class SingleDimensional {

    public static void main(String[] args) {

        // 1️⃣ Declaration and Initialization
        int[] numbers = {10, 20, 30, 40, 50};

        // 2️⃣ Accessing elements
        System.out.println("First element: " + numbers[0]);
        System.out.println("Last element: " + numbers[numbers.length - 1]);

        // 3️⃣ Traversing using for loop
        System.out.println("Array elements using for loop:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }

        // 4️⃣ Traversing using enhanced for loop
        System.out.println("\nArray elements using for-each:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }

        // 5️⃣ Updating an element
        numbers[2] = 99;
        System.out.println("\nAfter updating index 2:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }

        // 6️⃣ Find sum of elements
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("\nSum of elements: " + sum);

        // 7️⃣ Find maximum element
        int max = numbers[0];
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        System.out.println("Maximum element: " + max);
    }
}

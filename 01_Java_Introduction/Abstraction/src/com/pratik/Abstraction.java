package com.pratik;

abstract class Bank {

    // Abstract method (no body)
    abstract double getInterestRate();

    // Concrete method
    void displayBankInfo() {
        System.out.println("This is a Bank abstraction example");
    }
}

// Child class 1
class SBI extends Bank {

    @Override
    double getInterestRate() {
        return 6.5;
    }
}

// Child class 2
class HDFC extends Bank {

    @Override
    double getInterestRate() {
        return 7.0;
    }
}

public class Abstraction {

    public static void main(String[] args) {

        Bank sbi = new SBI();     // Abstraction via reference
        sbi.displayBankInfo();
        System.out.println("SBI Interest Rate: " + sbi.getInterestRate() + "%");

        Bank hdfc = new HDFC();
        System.out.println("HDFC Interest Rate: " + hdfc.getInterestRate() + "%");
    }
}
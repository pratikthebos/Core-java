package com.pratik;


//Parent class
class Animal {

 void sound() {
     System.out.println("Animal makes a sound");
 }
}

//Child class 1
class Dog extends Animal {

 @Override
 void sound() {
     System.out.println("Dog barks");
 }
}

//Child class 2
class Cat extends Animal {

 @Override
 void sound() {
     System.out.println("Cat meows");
 }
}

public class Polimorphism {

 public static void main(String[] args) {

     Animal animal;   // Parent reference

     animal = new Dog();   // Runtime polymorphism
     animal.sound();

     animal = new Cat();   // Runtime polymorphism
     animal.sound();
 }
}

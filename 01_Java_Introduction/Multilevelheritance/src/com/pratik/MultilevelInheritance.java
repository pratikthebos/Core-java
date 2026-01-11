package com.pratik;



//Multilevel Inheritance Example

//Grandparent class
class Animal {
 void eat() {
     System.out.println("Animal is eating");
 }
}

//Parent class (inherits Animal)
class Dog extends Animal {
 void bark() {
     System.out.println("Dog is barking");
 }
}

//Child class (inherits Dog)
class Puppy extends Dog {
 void play() {
     System.out.println("Puppy is playing");
 }
}

//Main class
public class MultilevelInheritance {
 public static void main(String[] args) {

     Puppy puppy = new Puppy();

     // Accessing methods from all levels
     puppy.eat();   // From Animal
     puppy.bark();  // From Dog
     puppy.play();  // From Puppy
 }
}




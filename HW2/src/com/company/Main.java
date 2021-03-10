package com.company;

import com.company.dto.Cleaning;
import com.company.dto.Disease;
import com.company.dto.Feeding;
import com.company.pojo.Animal;
import com.company.pojo.Aviary;
import com.company.pojo.Employee;
import com.company.pojo.Zoo;

public class Main {

    public static void main(String[] args) {
        Employee anton = new Employee("Anton");
        Employee igor = new Employee("Igor");
        Employee vlad = new Employee("Vlad");
        Employee[] employees = {anton, igor, vlad};

        Aviary aviary1 = new Aviary("1");
        Aviary aviary2 = new Aviary("2");
        Aviary aviary3 = new Aviary("3");
        Aviary[] aviaries = {aviary1, aviary2, aviary3};

        Zoo zoo = new Zoo(employees, aviaries);

        System.out.println("Init Zoo");
        Animal lionJoe = new Animal("Lion Joe");
        Animal lionBob = new Animal("Lion Bob");
        Animal lionMike = new Animal("Lion Mike");
        zoo.addAnimal(lionJoe, anton, aviary1);
        zoo.addAnimal(lionBob, vlad, aviary1);
        zoo.addAnimal(lionMike, vlad, aviary2);
        System.out.println(zoo);

        System.out.println("Remove Lion Joe");
        zoo.removeAnimal(lionJoe);
        System.out.println(zoo);

        System.out.println("Add obj from dto package");
        zoo.addCleaning(aviary1, new Cleaning("01.01.01"));
        zoo.addFeeding(lionBob, new Feeding("01.01.01"));
        zoo.addDisease(lionMike, new Disease("COVID"));
        System.out.println(zoo);

        System.out.println("Remove Lion Mike");
        zoo.removeAnimal(lionMike);
        System.out.println(zoo);
    }
}

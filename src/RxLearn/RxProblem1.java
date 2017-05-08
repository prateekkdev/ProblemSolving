/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RxLearn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Predicate;

class Person {

    String fName;
    String lName;
    String age;

    public Person(String fName, String lName, String age) {
        this.fName = fName;
        this.lName = lName;
        this.age = age;
    }

    @Override
    public String toString() {
        return fName + ", " + lName + ", " + age;
    }
}

/**
 *
 * @author prateek.kesarwani
 */
public class RxProblem1 {

    public RxProblem1() {
        ArrayList<Person> personDataList = new ArrayList<>();

        personDataList.add(new Person("Prateek", "Kesarwani", "28"));
        personDataList.add(new Person("Aman", "Kesarwani", "26"));
        personDataList.add(new Person("Sushma", "Pathak", "28"));
        personDataList.add(new Person("Usha", "Kesarwani", "54"));
        personDataList.add(new Person("Sudhir", "Kesarwani", "55"));
        personDataList.add(new Person("Akshay", "Kumar", "48"));

        Collections.sort(personDataList, (o1, o2) -> o1.fName.compareTo(o2.fName));

        System.out.println("Print All");
        printWithTest(personDataList, (p) -> true);

        System.out.println("Print lName Starting with K");
        printWithTest(personDataList, (p) -> p.lName.startsWith("K"));

        System.out.println("Print fName startring S");
        printWithTest(personDataList, (p) -> p.fName.startsWith("S"));

        performOperation(personDataList, (p) -> true, (p) -> System.out.println(p));

        System.out.println("\nPrint using method reference");
        performOperation(personDataList, p -> p.fName.startsWith("P"), System.out::println);
    }

    public void printWithTest(ArrayList<Person> personList, Predicate<Person> condition) {
        for (Person person : personList) {
            if (condition.test(person)) {
                System.out.println(person);
            }
        }

        personList.forEach(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                if (condition.test(person)) {
                    System.out.println(person);
                }
            }
        });

        personList.forEach((person) -> {
            if (condition.test(person)) {
                System.out.println(person);
            }
        });
    }

    public void performOperation(ArrayList<Person> personList, Predicate<Person> predicate, Consumer<Person> consumer) {

        personList.forEach((person) -> {
            if (predicate.test(person)) {
                consumer.accept(person);
            }
        });

    }
}

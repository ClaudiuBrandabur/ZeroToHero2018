package com.java_8_training.problems.streams;


import java.util.*;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.toList;

public class FiltersAndMapExercise {


    public static  List<Integer> findEvenNumbers() {
        Stream<Integer> input = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> evenNumbers = new ArrayList<>();
        evenNumbers = input.filter(x -> x % 2 == 0).collect(toList());

        return evenNumbers;

    }

    public static List<String> findPersonByName(String name){
        Stream<String> input = Stream.of("Jack", "James", "Henry", "John", "Ben", "Martin", "Tori");

        List<String> personName = new ArrayList<>();
        personName = input.filter(nume -> nume.equals("Jack")).collect(toList());
        return personName;
    }


    public static List<Integer> filterNull(){
        Stream<Integer> input = Stream.of(0,null, 1, 2, 3, 4, 5, null, 6, 7, 8, 9, 10, null);

        List<Integer> notNull = new ArrayList<>();
        notNull = input.filter(xx -> xx != null).collect(toList());

        return notNull;
    }

    public static List<Person> findPersonByAgeAndName(List<Person> personList, String name, Integer age){
        List<Person> persons = new ArrayList<>();
        persons = personList.stream().filter(person -> person.getAge().equals(age) && person.getName().equals(name)).collect(toList());
        return persons;
    }

    public static List<Integer> doubleNumbers() {
        Stream<Integer> input = Stream.of(0, 1, 2, 3, 4, 5);
        // HINT: use map
        List<Integer> doubleNumbers = new ArrayList<>();
        doubleNumbers = input.map(x -> x * 2).collect(toList());
        return doubleNumbers;
    }

    public static List<String> getOnlyNames(List<Person> personList){
        List<String> collect = new ArrayList<>();
        // HINT: use map
        collect = personList.stream().map(x -> x.getName()).collect(toList());
        return  collect;
    }

    public static List<Integer> flatteningLists() {
        Stream<List<Integer>> input = Stream.of(asList(1, 2), asList(3, 4));
        List<Integer> together = new ArrayList<>();

        together = input.flatMap(x -> x.stream()).collect(toList());

        return together;
    }


    public static int product(){
        Stream<Integer> input = Stream.of(1, 2, 3, 4, 5);

        int product = 1;
        product = input.reduce(1,(pr, x)-> pr*x);
        return product;
    }


    public static Optional<Person> findFirstAllMalePersons(List<Person> personList){

        Optional<Person> personOptional = Optional.empty();
        personOptional = personList.stream().filter(p -> p.isMale()).findFirst();
        return personOptional;
    }

    public static boolean areAllPersonsBelowAge(List<Person> personList, Integer age){

        boolean overAge = false;
        overAge = personList.stream().allMatch(person -> person.getAge() < age);
        return overAge;
    }


    public static int findTheMinimumNumber(){
        Stream<Integer> input =  Stream.of(5, 2, 200, 33, 150, 0);
        // HINT: use reduce
        int min = 0;
        min = input.reduce(Integer.MAX_VALUE, (m, x) -> Integer.min(m, x));
        return min;
    }

    public static int findTheYoungestPerson(List<Person> personList){

        // HINT: use map and reduce
        int min = 0;
//        min = personList.stream().map(x -> x.getAge()).reduce(Integer.MAX_VALUE, (m,x) -> Integer.min(m,x));
        Comparator<Person> ageComparator = Comparator.comparingInt(Person::getAge);
        min = personList.stream().collect(minBy(ageComparator)).get();
        return min;
    }

    public static double findAgeAverage(List<Person> personList){
        double sum = 0;
        sum = personList.stream().map(y -> y.getAge()).reduce(0, (s,x)-> s+x);
        return  sum/personList.size();
    }

    public static List<Nationality> findNationalityByName(List<Person> personList){

        List<Nationality> persons = new ArrayList<>();

        // HINT use startsWith() for finding the names that starts with J, and distinct() to remove duplicates

        persons = personList.stream().filter(person -> person.getName().startsWith("J")).map(person -> person.getNationality()).distinct().collect(toList());
        return  persons;
    }


    public static Optional<Integer> findSquareDivisibleBy3(){

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);

        Optional<Integer> firstSquareDivisibleByThree = Optional.empty();
        firstSquareDivisibleByThree = someNumbers.stream().map(nr -> nr*nr).filter(nr-> nr % 3 == 0).findFirst();
        return firstSquareDivisibleByThree;
    }

    public static int sumStringLength(){
        List<String> strings = Arrays.asList("Hello","everyone","Java8", "is" , "here");
        int sum = 0;
        sum = strings.stream().map(s -> s.length()).reduce(0, (s,l)-> s+l);
        return sum;
    }

    public static  int sumSalary(List<Person> personList){
        int sum =0;
        sum = personList.stream().map(s -> s.getSalary()).reduce(0,(s, x)-> s + x);
        return sum;
    }
    public static void main(String[] args){
        List<Nationality> nationalities = Arrays.asList(
                new Nationality("UK"),
                new Nationality("US"),
                new Nationality("France"),
                new Nationality("Romania")

        );
        List<Person> persons = Arrays.asList(
                new Person("Jack", 30, true, nationalities.get(0), 2783),
                new Person("James", 20, true, nationalities.get(0), 4000),
                new Person("Ana", 20, true, nationalities.get(1), 3900),
                new Person("Henry", 40, true, nationalities.get(2),5999),
                new Person("John", 34, true, nationalities.get(3), 1400),
                new Person("Diana", 38, true, nationalities.get(1),7899),
                new Person("Ben", 23, true, nationalities.get(3), 9000),
                new Person("Martin", 20, true, nationalities.get(2), 7677),
                new Person("Jacob", 20, true, nationalities.get(2), 5677),
                new Person("Clara", 19, true, nationalities.get(0), 4900),
                new Person("Jessica", 54, false, nationalities.get(2), 2333),
                new Person("Tori", 67, true, nationalities.get(1), 4500)
        );

        System.out.println("Exercise 1: " + findEvenNumbers());
        System.out.println("Exercise 2: " + findPersonByName("Jack"));
        System.out.println("Exercise 3: " + filterNull());
        System.out.println("Exercise 4: " +   findPersonByAgeAndName(persons,"James", 20));
        System.out.println("Exercise 5: " +   doubleNumbers());
        System.out.println("Exercise 6: " + getOnlyNames(persons));
        System.out.println("Exercise 7: " + flatteningLists());

        System.out.println("Exercise 8: " + product());
        System.out.println("Exercise 9: " + findFirstAllMalePersons(persons));

        System.out.println("Exercise 10: " + areAllPersonsBelowAge(persons, 70));
        System.out.println("Exercise 11: " + findTheMinimumNumber());
        System.out.println("Exercise 12: " + findTheYoungestPerson(persons));
        System.out.println("Exercise 13: " + findAgeAverage(persons));
        System.out.println("Exercise 14: " + findNationalityByName(persons));
        System.out.println("Exercise 15: " + findSquareDivisibleBy3());
        System.out.println("Exercise 16: " + sumStringLength());
        System.out.println("Exercise 17: " + sumSalary(persons));

    }
}

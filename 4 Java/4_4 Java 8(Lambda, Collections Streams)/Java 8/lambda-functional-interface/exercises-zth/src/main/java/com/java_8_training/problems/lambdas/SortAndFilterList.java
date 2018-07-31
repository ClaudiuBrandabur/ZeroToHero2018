package com.java_8_training.problems.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.lang.Character.isUpperCase;

public class SortAndFilterList {

    public static void main(String[] args) {
        sortAndFilterList();
    }

    private static void sortAndFilterList() {
        List<String> animals = Arrays.asList("lion", "bear", "dear", "Dog", "Cat", "mouse", "cougar", "elephant", "giraffe", "lemur", "Bison", "chimpanzee", "hyena", "cheetah");

        // TODO use lambda and functional interfaces / method references to sort by
        // 1. length
        animals.sort((a,b)-> (a.length()- b.length())); // -> lambda expr

        sortMe(animals,new MyLengthSortingClass()); // -> using interface

        MyLengthSortingClass lengthSort = new MyLengthSortingClass() ;
        animals.sort((a,b)-> lengthSort.mySort(a,b));
        animals.sort((a,b)-> new MyLengthSortingClass().mySort(a,b));
        animals.sort(lengthSort::mySort); //-> method reference
        System.out.println(animals);

        // 2. reverse length
        sortMeReverse(animals,new MyReverse());
        System.out.println(animals);

        animals.sort((a,b)-> b.length()- a.length());
        System.out.println(animals);

        MyReverse rev = new MyReverse();
        animals.sort(rev::mySort);
        System.out.println(animals);

        // 3. alphabetically
        animals.sort((a,b)->a.compareTo(b));
        System.out.println(animals);

        animals.sort(String::compareTo);
        System.out.println(animals);

        sortMeAlpha(animals, new MyAlpha() );
        System.out.println(animals);

        // 4. put the strings that contain 'e' first in the list. The other ones last.
        List<String> newList = containsE(animals);
        System.out.println(newList);

        // 5. filter only the strings that have the first letter capitalized
        // Function <List<String>,List<String>> f = MyNewClass :: container; ->>>>>

        List <String> myList = filter(animals);
        System.out.println(myList);

        //myList = List ::filter;
        //myList = (animals) -> filter(animals);

    }

    static void sortMe (List<String> list, MyInterface i){
        list.sort((a,b)->i.mySort(a,b));
    }

    static void sortMeReverse(List<String> list, MyInterface i){
        list.sort((a,b)->i.mySort(a,b));
    }

    static void sortMeAlpha(List<String> list, MyInterface i){
        list.sort((a,b)->i.mySort(a,b));
    }

    static List<String> filter(List<String> myList){

        List <String> anotherList = new ArrayList<>();
        for(String s: myList)
            if(isUpperCase(s.charAt(0)))
                anotherList.add(s);

        return anotherList;
    }

    static List<String> containsE(List<String> myList){
        List <String> anotherList = new ArrayList<>();
        List <String> finalList = new ArrayList<>();

        for(String s: myList) {
            if (s.contains("e"))
                finalList.add(s);
            else
                anotherList.add(s);
        }

        finalList.addAll(anotherList);
        return finalList;
    }

}

interface MyInterface {
    public int mySort(String a, String b);
}

interface MyInterface2{
    public List<String> container (List<String> myList);
}

class MyLengthSortingClass implements MyInterface{

    @Override
    public int mySort(String a, String b) {
        return a.length() - b.length();
    }
}

class MyReverse implements MyInterface{

    @Override
    public int mySort(String a, String b) {
        return b.length() - a.length();
    }
}

class MyAlpha implements MyInterface{

    @Override
    public int mySort(String a, String b) {
        return a.compareTo(b);
    }
}

class MyNewClass implements MyInterface2{

    @Override
    public List<String> container(List<String> myList) {

        List <String> anotherList = new ArrayList<>();
        List <String> finalList = new ArrayList<>();

        for(String s: myList) {
            if (s.contains("e"))
                finalList.add(s);
            else
                anotherList.add(s);
        }

        finalList.addAll(anotherList);
        return finalList;
    }
}
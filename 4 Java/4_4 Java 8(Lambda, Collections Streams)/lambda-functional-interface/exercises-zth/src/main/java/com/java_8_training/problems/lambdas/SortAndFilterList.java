package com.java_8_training.problems.lambdas;

import java.util.Arrays;
import java.util.List;

public class SortAndFilterList {

    public static void main(String[] args) {
        sortAndFilterList();
    }

    private static void sortAndFilterList() {
        List<String> animals = Arrays.asList("lion", "bear", "dear", "Dog", "Cat", "mouse", "cougar", "elephant", "giraffe", "lemur", "Bison", "chimpanzee", "hyena", "cheetah");

        // TODO use lambda and functional interfaces / method references to sort by
        // 1. length
        // 2. reverse length
        // 3. alphabetically
        // 4. put the strings that contain 'e' first in the list. The other ones last.
        // 5. filter only the strings that have the first letter capitalized

        sorting(animals, new LengthSort());
        sorting(animals, new ReverseLengthSort());
        sorting(animals, new AlphabeticallySort());
    }
    public static void sorting(List<String> animals, SortInterface s){
        for(String animal : animals){
            s.sortare();
        }
    }

}

interface SortInterface{
    public int sortare(String a, String b);
}

class LengthSort implements SortInterface{
    @Override
    public int sortare(String a, String b) {
        return (a.length() - b.length());
    }
}

class ReverseLengthSort implements SortInterface{
    @Override
    public int sortare(String a, String b) {
        return (b.length() - a.length());
    }
}

class AlphabeticallySort implements SortInterface{
    @Override
    public int sortare(String a, String b) {
        return a.compareTo(b);
    }
}
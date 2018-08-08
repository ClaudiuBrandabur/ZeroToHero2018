package com.java_8_training.problems.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
        animals.sort(Comparator.comparingInt(String::length));

        Comparator<String> comparator = String::compareToIgnoreCase;
        animals.sort(comparator.reversed());

        animals.sort(String::compareTo);

        animals.stream().filter(s -> Character.isUpperCase(s.charAt(0)));
    }
}

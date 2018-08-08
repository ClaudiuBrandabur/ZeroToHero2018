package com.java_8_training.problems.collectors;

import joptsimple.internal.Strings;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static junit.framework.Assert.assertEquals;

@Ignore
public class OccurrencesTest {

    @Test
    public void occurrencesForAWord() {
        String word = "cool";
        //TODO #C9
        Map<Character, Long> occ = new HashMap<>();
        for (int i = 0; i < word.length(); i++)
            if (!occ.containsKey(word.charAt(i)))
                occ.put(word.charAt(i), (long) 1);
            else
                occ.put(word.charAt(i), occ.get(word.charAt(i)) + 1);
        assertEquals(2, (long) occ.get('o'));
        assertEquals(1, (long) occ.get('c'));
        assertEquals(1, (long) occ.get('l'));
        assertEquals(3, occ.size());

    }

    @Test
    public void occurrencesForAListOfSentences() {
        List<String> sentences = Arrays.asList("Hello everyone!", "Java 8 is here!");

        //TODO #C9
        Map<Character, Long> occ = new HashMap<>();
        for (int i = 0; i < sentences.size(); i++)
        {
            String str = sentences.get(i);
            for (int j = 0; j < str.length(); j++)
                if (!occ.containsKey(str.charAt(j)))
                    occ.put(str.charAt(j), (long) 1);
                else
                    occ.put(str.charAt(j), occ.get(str.charAt(j)) + 1);
        }
        assertEquals(2, (long) occ.get('l'));
        assertEquals(4, (long) occ.get(' '));
        assertEquals(2, (long) occ.get('!'));
        assertEquals(2, (long) occ.get('a'));
        assertEquals(6, (long) occ.get('e'));
        assertEquals(16, occ.size());
    }

}


package com.java_8_training.problems.collectors;

import org.junit.Ignore;
import org.junit.Test;

import java.util.IntSummaryStatistics;
import java.util.OptionalInt;

import static com.java_8_training.problems.collectors.Dish.Type.MEAT;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.summarizingInt;
import static junit.framework.Assert.assertEquals;

@Ignore
public class ArithmeticAndReducingCollectorsTest {

    // See: Dish.menu.stream()

    @Test
    public void leastCaloricDishMEAT() {
        //TODO #C5
        Dish leastCaloricMEAT = new Dish();
        leastCaloricMEAT = Dish.menu.stream().filter(dish -> dish.getType() == MEAT).
                min((dish1, dish2) -> Integer.compare(dish1.getCalories(), dish2.getCalories())).get();
        assertEquals("chicken", leastCaloricMEAT.getName());
    }

    @Test
    public void statisticsForVegetarianDishes() {
        //TODO #C5
        IntSummaryStatistics vegetarianStats = new IntSummaryStatistics();
//        long count = Dish.menu.stream().mapToInt(Dish::getCalories).count();
//        long sum = Dish.menu.stream().mapToInt(Dish::getCalories).count();
//        double avg = sum / count;
//        OptionalInt max = Dish.menu.stream().mapToInt(Dish::getCalories).max();
//        OptionalInt min = Dish.menu.stream().mapToInt(Dish::getCalories).min();
        vegetarianStats = Dish.menu.stream().filter(dish -> dish.isVegetarian()).
                collect(summarizingInt(Dish::getCalories));

        assertEquals(4, vegetarianStats.getCount());
        assertEquals(1550, vegetarianStats.getSum());
        assertEquals(120, vegetarianStats.getMin());
        assertEquals(387.5, vegetarianStats.getAverage());
        assertEquals(550, vegetarianStats.getMax());

    }
}

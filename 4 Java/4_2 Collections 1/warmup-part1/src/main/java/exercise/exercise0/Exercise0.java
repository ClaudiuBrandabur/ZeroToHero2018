package exercise.exercise0;
import java.util.*;
/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Create a List (ArrayList or LinkedList), add elements to it and print all of them using ListIterator
 *             for loop and foreach loop.
 *
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughList(){

        List<Integer> myList = new ArrayList<Integer>();
        myList.add(0, 6);
        myList.add(1, 66);
        myList.add(2, 63);
        ListIterator it = myList.listIterator();
        //interatie cu listIteration
        while(it.hasNext()){
            System.out.println(it.next());
        }

        //iteratie cu for
        int i;

        for(i = 0; i < myList.size(); i++){
            System.out.println(myList.get(i));
        }

        //iteratie cu foreach

        for(int in : myList){
            System.out.println(in);
        }


        // TODO Exercise #0 a) Create a list (ArrayList or LinkedList) and add elements to it
        // TODO Exercise #0 a) Don't forget to specify the type of the list (Integer, String etc.)

        // TODO Exercise #0 b) Iterate through the list using ListIterator and print all its elements

        // TODO Exercise #0 c) Iterate through the list using classic for loop and print all its elements

        // TODO Exercise #0 d) Iterate through the list using foreach loop and print all its elements

    }

    public static void main(String[] args) {
        // TODO Exercise #0 e) Create a new instance of Exercise0 class and call the iterateThroughList() method
        Exercise0 ex = new Exercise0();
        ex.iterateThroughList();
    }
}

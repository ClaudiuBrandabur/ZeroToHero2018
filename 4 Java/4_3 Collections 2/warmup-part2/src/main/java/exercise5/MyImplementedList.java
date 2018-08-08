package exercise5;

import java.util.Comparator;
import java.util.Iterator;

public class MyImplementedList<E> implements Iterable<E>{

    /**
     * The maximum accepted load property of the data structure.
     */
    private static final double LOAD_FACTOR = 0.75d;

    /**
     * The factor for increasing the size of the data structure.
     */
    private static final int INCREASE_SIZE_FACTOR = 2;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    private int size;

    /**
     * The array buffer into which the elements of the {@link MyImplementedList} are stored.
     */
    private Object[] elementData;

    /**
     * Property to keep the extended capacity.
     * TODO if you choose another way to implement the extending capacity you can define your own properties,
     * TODO but the rest of them must remain as they are.
     */
    private int capacityAfterExtending;

    //TODO a) implement the empty constructor for the your data structure
    public MyImplementedList() {
        //TODO a) HINT - DEFAULT_CAPACITY, capacityAfterExtending and elementData properties
        capacityAfterExtending = DEFAULT_CAPACITY;
        size = 0;
        elementData = new Object[capacityAfterExtending];
    }

    //TODO b) create the int size() method that returns the size of the data structure
    public int size() {
        return size;
    }
    //TODO c) create the boolean add(E e) method that adds at the end of the data structure an element
    //TODO pay attention to the LOAD_FACTOR of the data structure
    public boolean add(E e) {
        extendCapacity();
        elementData[size] = e;
        size++;
        return true;
    }

    //TODO d) create the boolean isEmpty() method that checks if the data structure have elements
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }
    //TODO e) create the boolean contains(Object o_O) method that checks if the data structure contains the object o_O
    public boolean contains(E e) {
        if (indexOf(e) >= 0)
            return true;
        return false;
    }
    //TODO f) create the int indexOf(Object o_O) method that returns the position in the data structure of the object o_O
    //TODO if exists, otherwise return -1
    public int indexOf(E e) {
        if (e == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (elementData[i] == e)
                    return i;
        }
        return -1;
    }
    //TODO g) create the int lastIndexOf(Object o_O) method that returns the last position in the data structure of the object o_O
    //TODO if exists, otherwise return -1
    public int LastIndexOf(E e) {
        if (e == null) {
            for (int i = size - 1; i >= 0; i--)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
                if (e.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
    //TODO h) create the E get(int index) method that returns the object from the given index
    //TODO pay attention to the size property
    public E get(int index) {
        if (index >= size)
            throw new ArrayIndexOutOfBoundsException();
        return (E)elementData[index];
    }
    //TODO i) create the E set(int index, E element) method that updates the value of the element from the given index
    //TODO pay attention to the size property
    public E set(int index, E element) {
        if (index >= size)
            throw new ArrayIndexOutOfBoundsException();
        E oldValue = (E)elementData[index];
        elementData[index] = element;
        return oldValue;
    }
    // TODO j) create the E remove(int index) method that removes the element from the given index
    public E remove(int index) {
        if(index >= size)
            throw new ArrayIndexOutOfBoundsException();
        E oldValue = (E)elementData[index];
        for(int i = index; i < size - 1; i++)
            elementData[i] = elementData[i + 1];
        elementData[size] = null;
        size--;
        return oldValue;
    }
    //TODO k) extend the current default capacity, if the number of elements in the data structure is > 75% of it
    //TODO you should name it: void extendCapacity() - HINT use capacity, DEFAULT_CAPACITY, LOAD_FACTOR and INCREASE_SIZE_FACTOR
    public void extendCapacity() {
        if(size/DEFAULT_CAPACITY > LOAD_FACTOR){
            capacityAfterExtending = INCREASE_SIZE_FACTOR * capacityAfterExtending;
            Object[] newArray = new Object[capacityAfterExtending];
            for(int i = 0; i < size; i++)
                newArray[i] = elementData[i];
            elementData = newArray;
        }
    }
    //TODO l) implement the iterator() method in order to use the foreach statement over your data structure - HINT Iterable interface
    //TODO and implement a custom iterator for your custom data structure - methods boolean hasNext(), Object next() and void remove()

    public Iterator<E> iterator() {
            return new Iterat(this);
    }
    class Iterat implements Iterator<E>{

        int start = 0;
        MyImplementedList<E> impl_list;

        public Iterat(MyImplementedList<E> lst){
            this.impl_list = lst;
        }

        @Override
        public boolean hasNext() {
            if(start < impl_list.size())
                return true;
            return false;
        }

        @Override
        public E next() {
            if(hasNext()) {
                start++;
                return impl_list.get(start - 1);
            }
            return null;
        }

        @Override
        public void remove() {
            if(start == 0)
                throw new UnsupportedOperationException();
            impl_list.remove(start - 1);
            start --;
        }
    }
    //TODO m) implement a method, that uses a Comparator, for your data structure to sort the elements
    //TODO you should name it: void sort(Comparator<? super E> c)
    //TODO create a custom comparator that compares objects by their "what you want" :D - HINT Comparator interface
    public void sort (Comparator<?super E> c ) {

    }

    public class Comparator<E> {
        public int compare(E e1, E e2) {
            if (e1.hashCode() < e2.hashCode())
                return 1;
            return 0;
        }
    }
}


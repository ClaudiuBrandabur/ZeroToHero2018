package exercise6;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Create a resizable generic HashMap. When the number of entries exceeds (load capacity * bucket array size)
 * the HashMap needs to be resized.
 *
 * Resizing (rehashing) consists in increasing the size of the bucket array with the value of
 * INCREASE_SIZE_FACTOR. After this step, all the entries that were stored in the HashMap
 * must be inserted in the new bucket array according to the insertion algorithm in a HashMap:
 * the entry must be placed in a bucket after applying the hash function (hashcode modulo (bucket array size))
 * on the key's hashcode value. The result of the hash function will return the index from the
 * bucket array where the entry will be stored. (see HashMap documentation)
 *
 * Created by Radu.Hoaghe on 7/6/2017.
 */
public class MyResizableHashMap<K, V> {

    /**
     * The array of buckets.
     */
    private Node<K, V>[] buckets;

    /**
     * Default initial capacity for the bucket array.
     */
    private final int DEFAULT_BUCKET_ARRAY_SIZE = 16;

    /**
     * The maximum accepted load property of the data structure.
     */
    private static final double LOAD_FACTOR = 0.75d;

    /**
     * The factor for increasing the size of the data structure.
     */
    private static final int INCREASE_SIZE_FACTOR = 2;

    /**
     * The number of entries stored in the Map.
     */
    private int size;
    private int capacity ;

    public MyResizableHashMap() {

        // TODO Initialize buckets list
        size = 0;
        capacity = DEFAULT_BUCKET_ARRAY_SIZE;
        buckets = new Node[capacity];

    }

    private void resize() {
        // TODO function that does the rehashing of the HashMap
        if((capacity * LOAD_FACTOR) < size){

            Node<K, V>[] clone = buckets.clone();
            Node<K, V> iter;

            capacity *= INCREASE_SIZE_FACTOR;
            buckets = new Node[capacity];
            size = 0;

            for (int i = 0; i < clone.length; i++) {
                iter = clone[i];

                while (iter != null) {
                    put(iter.getEntry().getKey(), iter.getEntry().getValue());
                    iter = iter.getNextElement();
                }
            }
        }
    }

    public V get(K key) {
        // TODO
        if(key == null){
            Node <K,V> iter = buckets[0];

            while(iter != null){
                if(iter.getEntry().getKey() == null)
                    return iter.getEntry().getValue();
                iter = iter.getNextElement();
            }
            return null;
        }

        int index = Math.abs(key.hashCode() % capacity);
        Node <K,V> iter = buckets[index];

        while(iter != null) {
            if (iter.getEntry().getKey().equals(key))
                return iter.getEntry().getValue();
            //iter = iter.getNextElement();
        }

        return null;
    }

    public void put(K key, V value) {
        // TODO
        if (key == null) {
            Node<K, V> iter = buckets[0];
            boolean found = false;

            while (iter != null) {
                if (iter.getEntry().getKey() == null) {
                    iter.getEntry().setValue(value);
                    found = true;
                    break;
                }

                iter = iter.getNextElement();
            }

            if (!found) {
                buckets[0] = new Node<K, V>(new MyEntry<K, V>(key, value), 0, buckets[0]);
                size++;
            }
        } else {
            int bucketIndex = Math.abs(key.hashCode()) % capacity;
            Node<K, V> iter = buckets[bucketIndex];
            boolean found = false;

            while (iter != null) {
                if (iter.getEntry().getKey().equals(key)) {
                    iter.getEntry().setValue(value);
                    found = true;
                    break;
                }

                iter = iter.getNextElement();
            }

            if (!found) {
                buckets[bucketIndex] = new Node<K, V>(new MyEntry<K, V>(key, value), Math.abs(key.hashCode()) % capacity, buckets[bucketIndex]);
                size++;
            }
        }

        resize();

    }

    public Set<K> keySet() {
        // TODO
        HashSet<K> set = new HashSet<K>();
        Node<K, V> iter;

        for(int i = 0; i < buckets.length; i++){
            iter = buckets[i];

            while (iter != null) {
                set.add(iter.getEntry().getKey());
                iter = iter.getNextElement();
            }
        }

        return set;
    }

    public Collection<V> values() {
        // TODO
        HashSet set = new HashSet();
        Node<K, V> iter;

        for (int i = 0; i < buckets.length; i++){
            iter = buckets[i];

            while(iter != null){
                set.add(iter.getEntry().getValue());
                iter =iter.getNextElement();
            }
        }

        return set;
    }

    public V remove(K key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        int bucketIndex = Math.abs(key.hashCode()) % capacity;
        Node<K, V> iter = buckets[bucketIndex];
        Node<K, V> prev = null;
        V value = null;

        while (iter != null) {
            if (iter.getEntry().getKey().equals(key)) {
                value = iter.getEntry().getValue();
                size--;

                if (prev == null)
                    buckets[bucketIndex] = iter.getNextElement();
                else
                    prev.setNextElement(iter.getNextElement());
            }

            prev = iter;
            iter = iter.getNextElement();
        }

        return value;
    }

    public boolean containsKey(K key) {
        // TODO
        if (key == null) {
            Node<K, V> iter = buckets[0];

            while (iter != null) {
                if (iter.getEntry().getKey() == null)
                    return true;
                iter = iter.getNextElement();
            }

            return false;
        }

        int bucketIndex = Math.abs(key.hashCode()) % capacity;
        Node<K, V> iter = buckets[bucketIndex];

        while (iter != null) {
            if (iter.getEntry().getKey().equals(key))
                return true;
            iter = iter.getNextElement();
        }

        return false;

    }

    public boolean containsValue(V value) {
        // TODO
        Node<K, V> iter;

        for (int i = 0; i < buckets.length; i++) {
            iter = buckets[i];

            while (iter != null) {
                if ((value == null && iter.getEntry().getValue() == null) || iter.getEntry().getValue().equals(value))
                    return true;
                iter = iter.getNextElement();
            }
        }

        return false;
    }

    public int size() {
        // TODO Return the number of the Entry objects stored in all the buckets
        return size;
    }

    public void clear() {
        // TODO Remove all the Entry objects from the bucket list
        for (int i = 0; i < buckets.length; i++)
            buckets[i] = null;
        size = 0;
    }

    public Set<MyEntry> entrySet() {
        // TODO Return a Set containing all the Entry objects
        Set<MyEntry> entries = new HashSet<MyEntry>();
        Node<K, V> iter;

        for (int i = 0; i < buckets.length; i++) {
            iter = buckets[i];

            while (iter != null) {
                entries.add(iter.getEntry());
                iter = iter.getNextElement();
            }
        }

        return entries;
    }

    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    public static class MyEntry<K, V> {
        private K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    static class Node<K, V> {
        private final MyEntry<K, V> entry;
        private final int hash;
        private Node<K, V> nextElement;

        public Node(MyEntry<K, V> entry, int hash, Node<K, V> nextElement) {
            this.entry = entry;
            this.hash = hash;
            this.nextElement = nextElement;
        }

        public MyEntry<K, V> getEntry() {
            return entry;
        }

        public int getHash() {
            return hash;
        }

        public Node<K, V> getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node<K, V> nextElement) {
            this.nextElement = nextElement;
        }
    }
}

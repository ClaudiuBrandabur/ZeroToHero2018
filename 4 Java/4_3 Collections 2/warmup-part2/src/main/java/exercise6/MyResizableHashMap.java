package exercise6;

import exercise4.MyHashMap;

import java.util.*;

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
    private int arraySize;

    public MyResizableHashMap() {

        // TODO Initialize buckets list
        size = 0;
        arraySize = DEFAULT_BUCKET_ARRAY_SIZE;
        buckets = new Node[arraySize];
    }

    private void resize() {
        // TODO function that does the rehashing of the HashMap
        if (arraySize * LOAD_FACTOR < size)
        {
            Node<K, V>[] clone = buckets.clone();
            Node<K, V> iterator;
            arraySize *= INCREASE_SIZE_FACTOR;
            buckets = new Node[arraySize];
            size = 0;
            for (int i = 0; i < clone.length; i++)  {
                iterator = clone[i];
                while (iterator != null) {
                    put(iterator.getEntry().getKey(), iterator.getEntry().getValue());
                    iterator = iterator.getNextElement();
                }
            }
        }
    }

    public V get(K key) {
        // TODO
        if (key == null) {
            Node<K, V> first = buckets[0];
            while (first != null) {
                if (first.getEntry().getKey() == null)
                    return first.getEntry().getValue();
                first = first.getNextElement();
            }
            return null;
        }
        int index = Math.abs(key.hashCode() % arraySize);
        Node<K, V> point = buckets[index];
        while (point != null) {
            if (point.getEntry().getKey().equals(key))
                return point.getEntry().getValue();
        }
        return null;
    }

    public void put(K key, V value) {
        // TODO
        if (key == null) {
            Node<K, V> first = buckets[0];
            boolean ok = false;
            while (first != null) {
                if (first.getEntry().getKey() == null) {
                    first.getEntry().setValue(value);
                    ok = true;
                    break;
                }
                first = first.getNextElement();
            }
            if (!ok) {
                buckets[0] = new Node<K, V>(new MyEntry<K, V>(key, value), 0, buckets[0]);
                size++;
            }
        } else {
            int index = Math.abs(key.hashCode() % arraySize);
            Node<K, V> point = buckets[index];
            boolean ok = false;
            while (point != null) {
                if (point.getEntry().getKey().equals(key)) {
                    point.getEntry().setValue(value);
                    ok = true;
                    break;
                }
                point = point.getNextElement();
            }
            if (!ok) {
                buckets[index] = new Node<K, V>(new MyEntry<K, V>(key, value), index, buckets[index]);
                size++;
            }
        }
        resize();
    }

    public Set<K> keySet() {
        // TODO
        HashSet<K> set = new HashSet<K>();
        Node<K, V> point;

        for (int i = 0; i < buckets.length; i++) {
            point = buckets[i];
            while (point != null) {
                set.add(point.getEntry().getKey());
                point = point.getNextElement();
            }
        }
        return set;
    }

    public Collection<V> values() {
        // TODO
        HashSet<V> set = new HashSet<V>();
        Node<K, V> point;

        for (int i = 0; i < buckets.length; i++) {
            point = buckets[i];
            while (point != null) {
                set.add(point.getEntry().getValue());
                point = point.getNextElement();
            }
        }
        return set;
    }

    public V remove(K key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        int index = Math.abs(key.hashCode() % arraySize);
        Node<K, V> point = buckets[index];
        Node<K, V> before = null;
        V value = null;

        while (point != null) {
            if (point.getEntry().getKey().equals(key)) {
                value = point.getEntry().getValue();
                size--;
                if (before == null)
                    buckets[index] = point.getNextElement();
                else
                    before.setNextElement(point.getNextElement());
            }
            before = point;
            point = point.getNextElement();
        }
        return value;
    }

    public boolean containsKey(K key) {
        // TODO
        if (key == null) {
            Node<K, V> point = buckets[0];
            while (point != null) {
                if (point.getEntry().getKey() == null)
                    return true;
                point = point.getNextElement();
            }
            return false;
        }
        int index = Math.abs(key.hashCode() % arraySize);
        Node<K, V> point = buckets[index];
        while (point != null) {
            if (point.getEntry().getKey().equals(key))
                return true;
        }
        return false;
    }

    public boolean containsValue(V value) {
        // TODO
        Node<K, V> point;

        for (int i = 0; i <buckets.length ; i++) {
            point = buckets[i];
            while (point != null) {
                if ((value == null && point.getEntry().getValue() == null) || point.getEntry().getValue().equals(value))
                    return true;
                point = point.getNextElement();
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
        Set<MyEntry> entry = new HashSet<MyEntry>();
        Node<K, V> point;
        for (int i = 0; i < buckets.length; i++) {
            point = buckets[i];
            while (point != null) {
                entry.add(point.getEntry());
                point = point.getNextElement();
            }
        }
        return entry;
    }

    public boolean isEmpty() {
        // TODO
        if (size == 0)
            return true;
        return false;
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

package exercise5;

import exercise4.MyHashMap;

import java.util.*;

/**
 * Create a HashMap that uses to store the buckets your implementation of MyImplementedList that you
 * created in the Collections I workshop.
 *
 * Created by Radu.Hoaghe on 7/6/2017.
 */

public class MyHashMapWithMyImplementedList {

    // TODO uncomment the following line and add your MyImplementedList implementation to the project
    private MyImplementedList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;

    public MyHashMapWithMyImplementedList() {
        // TODO
        buckets = new MyImplementedList<LinkedList<MyEntry>>();
        for (int i = 0; i < BUCKET_ARRAY_SIZE; i++)
            buckets.add(new LinkedList<MyEntry>());
    }

    public String get(String key) {
        // TODO
        if (key == null)
            return null;
        int index = Math.abs(key.hashCode()) % BUCKET_ARRAY_SIZE;
        for (int i = 0; i < buckets.get(index).size(); i++)
            if (buckets.get(index).get(i).getKey().equals(key))
                return buckets.get(index).get(i).getValue();
        return null;
    }

    public void put(String key, String value) {
        // TODO
        int index  = 0;

        if (key != null)
            index = Math.abs(key.hashCode()) % BUCKET_ARRAY_SIZE;

        for (int i = 0; i < buckets.get(index).size(); i++)
            if (buckets.get(index).get(i).getKey().equals(key)) {
                buckets.get(index).get(i).setValue(value);
                return;
            }
        buckets.get(index).add(new MyEntry(key, value));
    }

    public Set<String> keySet() {
        // TODO
        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < buckets.size(); i++) {
            for(int j = 0; j < buckets.get(i).size(); j++){
                set.add(buckets.get(i).get(j).getKey());
            }
        }
        return set;
    }

    public Collection<String> values() {
        // TODO
        ArrayList<String> collection = new ArrayList<String>();
        for (int i = 0; i < buckets.size(); i++) {
            for(int j = 0; j < buckets.get(i).size(); j++){
                collection.add(buckets.get(i).get(j).getValue());
            }
        }
        return collection;
    }

    public String remove(String key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        int index = 0;
        String oldValue = null;

        if (key != null)
            index = Math.abs(key.hashCode()) % BUCKET_ARRAY_SIZE;

        LinkedList<MyEntry> bucket = buckets.get(index);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).getKey().equals(key)) {
                oldValue = bucket.get(i).getValue();
                bucket.remove(i);
                return oldValue;
            }
        }
        return null;
    }

    public boolean containsKey(String key) {
        // TODO
        int index = Math.abs(key.hashCode()) % BUCKET_ARRAY_SIZE;
        LinkedList<MyEntry> bucket = buckets.get(index);
        for (int i = 0; i < bucket.size(); i++)
            if (bucket.get(i).getKey().equals(key))
                return true;
        return false;
    }

    public boolean containsValue(String value) {
        // TODO
        for (int i = 0; i < buckets.size(); i++)
            for (int j = 0; j < buckets.get(i).size(); j++) {
                if (buckets.get(i).get(j).getValue().equals(value))
                    return true;
            }
        return false;
    }

    public int size() {
        // TODO Return the number of the Entry objects stored in all the buckets
        int size = 0;
        for (int i = 0; i < buckets.size(); i++) {
            for (int j = 0; j < buckets.get(i).size(); j++)
                size++;
        }
        return size;
    }

    public void clear() {
        // TODO Remove all the Entry objects from the bucket list
        for (int i = 0; i < buckets.size(); i++)
            for (int j = 0; j < buckets.get(i).size(); j++)
                buckets.get(i).remove(j);
    }

    public Set<MyHashMap.MyEntry> entrySet() {
        // TODO Return a Set containing all the Entry objects
        Set<MyHashMap.MyEntry> set = new HashSet<MyHashMap.MyEntry>();
        for (int i = 0; i < buckets.size(); i++)
            for (int j = 0; j < buckets.get(i).size(); j++)
                set.add(new MyHashMap.MyEntry(buckets.get(i).get(j).getKey(), buckets.get(i).get(j).getValue()));
        return set;
    }

    public boolean isEmpty() {
        // TODO
        int size = size();
        if (size == 0)
            return true;
        return false;
    }

    public static class MyEntry {
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

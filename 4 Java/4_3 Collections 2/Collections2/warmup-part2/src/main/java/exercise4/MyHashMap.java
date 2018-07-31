package exercise4;

import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 *
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 * The hash function that you will use in order to store a pair in a specific bucket will be
 * the one presented earlier: (hashcode value) modulo (bucket array size)
 */
public class MyHashMap {

    private ArrayList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;

    public MyHashMap() {

        // TODO Initialize buckets list
        buckets = new ArrayList<LinkedList<MyEntry>>(BUCKET_ARRAY_SIZE);
        for(int i = 0; i < BUCKET_ARRAY_SIZE; i++){
            buckets.add(new LinkedList<MyEntry>());
        }
    }

    public String get(String key) {
        // TODO
        if(key == null)
            return null;

        int index = Math.abs(key.hashCode()% BUCKET_ARRAY_SIZE);

        for(int i = 0; i < buckets.get(index).size(); i++){
            if( buckets.get(index).get(i).getKey().equals(key))
                return buckets.get(index).get(i).getValue();
        }
        return null;
    }

    public void put(String key, String value) {
        // TODO
        int index = 0;

        if(key!=null)
            index = Math.abs(key.hashCode()% BUCKET_ARRAY_SIZE);

        for(int i = 0; i < buckets.get(index).size(); i++){
            if(buckets.get(index).get(i).getKey().equals(key)){
                buckets.get(index).get(i).setValue(value);
                return;
            }
        }

        buckets.get(index).add(new MyEntry(key,value));
    }

    public Set<String> keySet() {
        // TODO
        HashSet<String> s = new HashSet<String>();
        for(int i = 0; i<buckets.size(); i++)
            for(int j = 0; j<buckets.get(i).size(); j++)
                s.add(buckets.get(i).get(j).getKey());

        return s;
    }

    public Collection<String> values() {
        // TODO
        HashSet <String> s = new HashSet<String>();
        for(int i = 0; i < buckets.size(); i++)
            for(int j = 0; j < buckets.get(i).size(); j++)
                s.add(buckets.get(i).get(j).getValue());

        return s;
    }

    public String remove(String key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        int index = 0;
        String value = null;

        if(key != null)
            index = Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE);

        for(int i = 0; i < buckets.get(index).size(); i++)
            if(buckets.get(index).get(i).getKey().equals(key)) {
                value = buckets.get(index).get(i).getValue();
                buckets.get(index).remove(i);
                return value;
            }
        return null;
    }

    public boolean containsKey(String key) {
        // TODO
        for(int i = 0; i < buckets.size(); i++)
            for(int j = 0; j < buckets.get(i).size(); j++)
                if(buckets.get(i).get(j).getKey().equals(key))
                    return true;

        return false;
    }

    public boolean containsValue(String value) {
        // TODO
        for(int i = 0; i < buckets.size(); i++)
            for(int j = 0; j < buckets.get(i).size(); j++)
                if(buckets.get(i).get(j).getValue().equals(value))
                    return true;

        return false;
    }

    public int size() {
        // TODO Return the number of the Entry objects stored in all the buckets
        int size = 0;
        for(int i = 0; i < BUCKET_ARRAY_SIZE; i++)
            for(int j = 0; j < buckets.get(i).size(); j++)
                if(buckets.get(i).get(j) != null)
                    size ++;
        return size;
    }

    public void clear() {
        // TODO Remove all the Entry objects from the bucket list
        for(int i = 0; i < buckets.size(); i++)
            for(int j = 0; j < buckets.get(i).size(); j++)
                buckets.get(i).remove(j);
    }

    public Set<MyEntry> entrySet() {
        // TODO Return a Set containing all the Entry objects
        HashSet <MyEntry> s = new HashSet<MyEntry>();
        for(int i = 0; i < buckets.size(); i++)
            for(int j = 0; j < buckets.get(i).size(); j++)
                s.add(new MyEntry(buckets.get(i).get(j).getKey(),buckets.get(i).get(j).getValue()));

        return s;
    }

    public boolean isEmpty() {
        // TODO
        if(size() == 0) return true;
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

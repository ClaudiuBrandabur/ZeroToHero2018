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
        buckets = new MyImplementedList<LinkedList<MyEntry>>();
        for(int i = 0; i < BUCKET_ARRAY_SIZE; i++){
            buckets.add(new LinkedList<MyEntry>());
        }
    }

    public String get(String key) {
        if(key == null){
            return null;
        }
        int index = Math.abs(key.hashCode()) % BUCKET_ARRAY_SIZE;
        int i;
        for(i = 0; i < buckets.get(index).size(); i++){
            if(buckets.get(index).get(i).getKey().equals(key)){
                return buckets.get(index).get(i).getValue();
            }
        }
        return null;
    }

    public void put(String key, String value) {
        if(key == null) {
            buckets.get(0).add(new MyEntry(null, value));
            return;
        }
        int index = Math.abs(key.hashCode()) % BUCKET_ARRAY_SIZE;
        for(int i = 0; i < buckets.get(index).size(); i++){
            if(buckets.get(index).get(i).getKey().equals(key)){
                buckets.get(index).get(i).setValue(value);
                return;
            }
        }
        buckets.get(index).add(new MyEntry(key, value));
    }

    public Set<String> keySet() {
        HashSet keyset = new HashSet();
        for(int i = 0; i < buckets.size(); i++){
            for(int j = 0; j < buckets.get(i).size(); j++){
                keyset.add(buckets.get(i).get(j).getKey());
            }
        }
        return keyset;
    }

    public Collection<String> values() {
       ArrayList<String> values = new ArrayList<String>();
        for(int i = 0; i < buckets.size(); i++){
            for(int j = 0; j < buckets.get(i).size(); j++){
                values.add(buckets.get(i).get(j).getValue());
            }
        }
        return values;
    }

    public String remove(String key) {
        String value = new String();
        int index = Math.abs(key.hashCode()) % BUCKET_ARRAY_SIZE;
        for(int i = 0; i < buckets.get(index).size(); i++){
            if(buckets.get(index).get(i).getKey().equals(key))
                value = buckets.get(index).get(i).getValue();
            buckets.get(index).remove(i);
            return value;
        }
        return null;
    }

    public boolean containsKey(String key) {
        for(int i = 0; i < buckets.size(); i++){
            for(int j = 0; j < buckets.get(i).size(); j++){
                if(buckets.get(i).get(j).getKey().equals(key)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(String value) {
        for(int i = 0; i < buckets.size(); i++){
            for(int j = 0; j < buckets.get(i).size(); j++){
                if(buckets.get(i).get(j).getValue().equals(value)){
                    return true;
                }
            }
        }
        return false;
    }

    public int size() {
        int size = 0;
        for(int i = 0; i < buckets.size(); i++){
            for(int j = 0; j < buckets.get(i).size(); j++){
                size++;
            }
        }
        return size;
    }

    public void clear() {
        for(int i = 0; i < buckets.size(); i++){
            for(int j = 0; j < buckets.get(i).size(); j++){
                buckets.get(i).remove(j);
            }
        }
    }

    public Set<MyHashMap.MyEntry> entrySet() {
        HashSet <MyHashMap.MyEntry > hashSet = new HashSet<>();

        for(int i = 0; i < buckets.size(); i++)
            for(int j = 0; j < buckets.get(i).size(); j++)
                hashSet.add(new MyHashMap.MyEntry(buckets.get(i).get(j).getKey(),buckets.get(i).get(j).getValue()));
        return hashSet;
    }

    public boolean isEmpty() {
        int size;
        size = size();
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
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

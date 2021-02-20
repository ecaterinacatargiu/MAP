package ADT;

import java.util.HashMap;
import java.util.Map;

public class LockTable<K,V> implements ILockTable<K,V> {

    private Map<K,V> myMap = new HashMap<>();

    public LockTable() { myMap = (Map<K, V>) new HashMap<Integer, Integer>();}

    @Override
    public void addToLock(K key, V value) {
        myMap.put(key,value);
    }

    @Override
    public void updateLock(K key, V value) {
        myMap.put(key,value);
    }

    @Override
    public void removeFromLock(K key) {
        myMap.remove(key);
    }

    @Override
    public boolean isDefined(K key) {
        return myMap.containsKey(key);
    }

    @Override
    public int getSize() {
        return myMap.size();
    }

    @Override
    public V getValue(K key) {
        return myMap.get(key);
    }

    @Override
    public void setLock(Map<K, V> content) {
        myMap=content;
    }

    @Override
    public HashMap<Integer, Integer> getAll() {
        return (HashMap<Integer, Integer>) myMap.entrySet();
    }

    @Override
    public String toString(){
        StringBuffer str = new StringBuffer();
        for(Map.Entry<K, V> e : myMap.entrySet()){
            str.append("<"+e.getKey() + ", " + e.getValue() + ">; ");
        }
        return str.toString();
    }
}

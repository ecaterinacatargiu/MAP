package ADT;

import java.util.HashMap;
import java.util.Map;

public interface ILockTable<K, V> {

    void addToLock(K key, V value);
    void updateLock(K key, V value);
    void removeFromLock(K key);
    boolean isDefined(K key);
    int getSize();
    V getValue(K key);
    void setLock(Map<K, V> content);
    HashMap<Integer, Integer> getAll();
}

package ADT;

import java.util.HashMap;
import java.util.Map;

public class LatchTable<K,V> implements ILatchTable {

    HashMap<Integer, Integer> latchTable;
    int key;

    public LatchTable() {
        latchTable = new HashMap<>();
        key = 0;
    }

    @Override
    public boolean isDefined(int id) {
        return latchTable.containsKey(id);
    }

    @Override
    public int lookup(int id) {
        return latchTable.get(id);
    }

    @Override
    public void update(int id, int val) {
        latchTable.put(id, val);
    }

    @Override
    public int addToLatch(int val) {
        latchTable.put(key,val);
        key++;
        return key-1;
    }

    @Override
    public HashMap<Integer, Integer> getAll() {
        return latchTable;
    }

    @Override
    public String toString() {
        String res = "";
        for (Integer k : latchTable.keySet())
        {
            res += "\t" + String.valueOf(k) + "->" + latchTable.get(k) + "\n";
        }
        return res;
    }
}

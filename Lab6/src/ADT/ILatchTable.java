package ADT;

import Model.Values.Value;

import java.util.HashMap;
import java.util.Map;

public interface ILatchTable{

    boolean isDefined(int id);
    int lookup(int id);
    void update(int id, int val);
    int addToLatch(int val);

    HashMap<Integer, Integer> getAll();



}

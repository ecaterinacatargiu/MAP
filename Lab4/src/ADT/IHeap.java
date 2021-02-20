package ADT;

import Model.Values.ReferenceValue;
import Model.Values.Value;

import java.util.Map;

public interface IHeap {

    void addToHeap(Value v);
    int getCurrentFreeAddress();
    boolean isAddress(int address);
    Value getValue(int address);
    void updateHeap(int address, Value value);
    void setHeap(Map<Integer, Value> content);
    Map<Integer, Value> getHeapV();

}

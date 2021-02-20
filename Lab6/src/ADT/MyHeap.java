package ADT;
import Model.Values.Value;
import java.util.HashMap;
import java.util.Map;

public class MyHeap implements IHeap {

    private Map<Integer,Value> heap;
    private int heapFreeAddress;

    public MyHeap()
    {
        heap = new HashMap<Integer, Value>();
        heapFreeAddress = 0;
    }

    @Override
    public String toString(){
        String result = "";
        for(Integer k : heap.keySet())
        {
            result += "\t"+ String.valueOf(k)+" -> " + heap.get(k) + "\n";
        }
        return result;
    }


    @Override
    public void addToHeap(Value v)
    {
        heapFreeAddress++;
        heap.put(heapFreeAddress, v);

    }

    @Override
    public int getCurrentFreeAddress() {
        return heapFreeAddress;
    }

    @Override
    public boolean isAddress(int address)
    {
        return heap.containsKey(address);
    }

    @Override
    public Value getValue(int address)
    {
        return heap.get(address);
    }

    @Override
    public void updateHeap(int address, Value value)
    {
        heap.put(address, value);
    }

    @Override
    public void setHeap(Map<Integer, Value> content)
    {
        heap.clear();
        for(Integer heapFreeAddress: content.keySet())
        {
            heap.put(heapFreeAddress, content.get(heapFreeAddress));
        }

    }

    @Override
    public Map<Integer, Value> getHeapV()
    {
        return heap;
    }

    @Override
    public HashMap<Integer, Value> getAll() {
        return (HashMap<Integer, Value>) heap;
    }
}

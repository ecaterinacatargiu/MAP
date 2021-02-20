package sample.Utils;

import Model.Values.Value;

public class HeapObject {

    int address;
    Value value;

    public HeapObject(int address, Value value)
    {
        this.address = address;
        this.value = value;
    }

    public int getAddress()
    {
        return address;
    }

    public void setAddress(int address)
    {
        this.address = address;
    }

    public Value getValue()
    {
        return value;
    }

    public void setValue(Value value)
    {
        this.value = value;
    }

    public String toString()
    {
        return String.valueOf(address) + value.toString();
    }

}

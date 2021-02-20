package sample.Utils;

import Model.Values.Value;

public class LatchTableObj {

    int name;
    Value value;

    public LatchTableObj(int name, Value value)
    {
        this.name = name;
        this.value = value;
    }

    public int getName()
    {
        return name;
    }

    public void setName(int name)
    {
        this.name = name;
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
        return String.valueOf(name) +", "+ String.valueOf(value);
    }
}

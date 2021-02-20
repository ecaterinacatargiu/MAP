package sample.Utils;

import Model.Values.StringValue;
import Model.Values.Value;

public class SymbolTableObject {

    String name;
    Value value;

    public SymbolTableObject(String name, Value value)
    {
        this.name = name;
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
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
        return name.toString() + value.toString();
    }
}

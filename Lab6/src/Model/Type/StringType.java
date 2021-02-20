package Model.Type;

import Model.Values.StringValue;
import Model.Values.Value;

public class StringType implements Type{

    public StringType() {}

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof StringType)
            return true;
        else
            return false;
    }

    @Override
    public String toString() { return "string";}

    @Override
    public Value defaultValue() {
        return new StringValue("\"\"");
    }
}

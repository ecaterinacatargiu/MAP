package Model.Type;

import Model.Values.BoolValue;

public class BoolType implements Type
{
    @Override
    public boolean equals(Object obj)
    {
        if( obj instanceof BoolType)
            return true;
        else
            return false;
    }

    @Override
    public String toString()
    {
        return "bool";
    }
}

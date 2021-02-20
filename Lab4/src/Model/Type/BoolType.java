package Model.Type;

import Model.Values.BoolValue;
import Model.Values.Value;

import java.awt.print.Book;

public class BoolType implements Type
{
    public BoolType() {}

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

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

package Model.Values;

import Model.Type.BoolType;
import Model.Type.Type;

public class BoolValue implements Value
{
    private boolean val;

    public BoolValue(boolean v)
    {
        this.val=v;
    }

    public boolean getValue() { return val; }

    @Override
    public String toString()
    {
        return ( '('+String.valueOf(this.val)+')');
    }

    @Override
    public Type getType()
    {
        return new BoolType();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof BoolValue)
            if(((BoolValue)obj).getValue()==val)
            {
                return true;
            }
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        BoolValue clone = (BoolValue) super.clone();
        clone.val = this.val;
        return clone;
    }

}

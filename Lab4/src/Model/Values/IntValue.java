package Model.Values;

import Model.Type.IntType;
import Model.Type.Type;

public class IntValue implements Value
{
    public int val;
    public IntValue(int v)
    {
        this.val=v;
    }

    public int getValue() { return val; }

    @Override
    public Type getType()
    {
        return new IntType();
    }

    @Override
    public String toString()
    {
        return ('('+String.valueOf(this.val)+')');
    }

    @Override
    public boolean equals(Object obj)
    {
        if( obj instanceof IntValue)
            if(((IntValue) obj).getValue()==val){
                return true;}
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        IntValue clone = (IntValue) super.clone();
        clone.val = this.val;
        return clone;
    }

}

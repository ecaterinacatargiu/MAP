package Model.Values;

import Model.Type.IntType;
import Model.Type.Type;

public class IntValue implements Value<Integer>
{
    public int val;
    public IntValue(int v)
    {
        this.val=v;
    }

    @Override
    public Integer getValue()
    {
        return this.val;
    }

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
    public Value deepCopy(){
        return new IntValue(val);
    }
}

package Model.Values;

import Model.Type.BoolType;
import Model.Type.Type;

public class BoolValue implements Value<Boolean>
{
    private boolean val;

    public BoolValue(boolean v)
    {
        this.val=v;
    }

    @Override
    public Boolean getValue()
    {
        return this.val;
    }

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
    public Value deepCopy(){
        return new BoolValue(val);
    }

}

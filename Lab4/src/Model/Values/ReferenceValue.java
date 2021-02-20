package Model.Values;

import Model.Type.ReferenceType;
import Model.Type.Type;

public class ReferenceValue implements Value {

    private int address;
    private Type locationType;

    public ReferenceValue(Type locationType, int address){
        this.address=address;
        this.locationType=locationType;
    }

    @Override
    public Type getType(){
        return new ReferenceType(locationType);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ReferenceValue clone = (ReferenceValue) super.clone();
        clone.address = address;
        clone.locationType = (Type) locationType.clone();
        return clone;
    }

    public Integer getAddress(){
        return address;
    }

    @Override
    public String toString()
    {
        return ( '('+String.valueOf(this.address)+", " +locationType.toString()+')');
    }



}

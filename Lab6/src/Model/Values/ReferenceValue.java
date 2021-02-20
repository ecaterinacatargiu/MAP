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

    public Integer getAddress(){
        return address;
    }

    @Override
    public String toString()
    {
        return ( '('+String.valueOf(this.address)+", " +locationType.toString()+')');
    }



}

package Model.Type;

import Model.Values.ReferenceValue;

public class ReferenceType implements Type {

    private Type inner;
    public ReferenceType(Type inner){
        this.inner=inner;
    }


    public Type getInnerType(){
        return inner;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof ReferenceType)
            return inner.equals(((ReferenceType) obj).getInnerType());
        else
            return false;
    }

    public String toString(){
        if(inner == null)
            return "&";
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public ReferenceValue defaultValue() {
        return new ReferenceValue(inner, 0);
    }
}

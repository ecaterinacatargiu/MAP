package Model.Values;

import Model.Type.Type;

public interface Value extends Cloneable{

    Type getType();
    public boolean equals(Object obj);

    Object clone() throws CloneNotSupportedException;
}

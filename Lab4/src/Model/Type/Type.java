package Model.Type;

import Model.Values.Value;

public interface Type extends Cloneable{

    Value defaultValue();

    Object clone() throws CloneNotSupportedException;
}

package Model.Values;

import Model.Type.Type;

public interface Value<T>{

    Type getType();
    T getValue();

    Value deepCopy();
}

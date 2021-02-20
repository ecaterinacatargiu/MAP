package Model.Values;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value {
    public String string;

    public StringValue(String string) {
        this.string = string;
    }

    public String getValue() {
        return this.string;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public String toString() {
        return ('(' + string+ ')');
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StringValue)
            if (((StringValue) obj).getValue() == string) {
                return true;
            }
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        StringValue clone = (StringValue) super.clone();
        clone.string = this.string;
        return clone;
    }
}

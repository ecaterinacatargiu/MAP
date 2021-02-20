package ADT;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.util.Hashtable;

public interface IFileTable<StringValue, BufferReader> {

    boolean isDefined(StringValue name);
    void add(StringValue name, BufferedReader fd);
    BufferedReader get(StringValue v);
    void delete(StringValue v);

    Hashtable<StringValue, BufferReader> getAll();
}

package ADT;

import Model.Values.StringValue;

import java.io.BufferedReader;
import java.util.Hashtable;

public class MyFileTable<StringValue, BufferReader> implements IFileTable<StringValue, BufferReader>{

    private Hashtable<StringValue, BufferedReader> filetable;

    public MyFileTable(){ filetable = new Hashtable<StringValue, BufferedReader>(); }

    @Override
    public boolean isDefined(StringValue name) {
        return filetable.containsKey(name);
    }

    @Override
    public void add(StringValue name, BufferedReader fd)
    {
        filetable.put(name,fd);
    }

    @Override
    public BufferedReader get(StringValue v) {
        return filetable.get(v);
    }

    @Override
    public void delete(StringValue v) {
        filetable.remove(v);
    }

    @Override
    public String toString()
    {
        String ft = "";
        for(StringValue s: filetable.keySet())
        {
            ft += "\t"+s.toString()+"\n";
        }
        return ft;
    }
}

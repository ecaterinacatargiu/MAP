package ADT;

import Model.Type.MyException;
import java.util.HashMap;

import javax.management.openmbean.KeyAlreadyExistsException;

public class Dictionary<T1,T2> implements IDictionary<T1,T2>
{
    private HashMap<T1,T2> dictionary;

    public Dictionary()
    {
        this.dictionary=new HashMap<T1,T2>();
    }

    @Override
    public void add(T1 key, T2 value) throws Exception
    {
        if(key==null || value==null)
            throw new NullPointerException("You cannot add null values");
        if(dictionary.containsKey(key))
            throw new Exception("Key already exists");
        this.dictionary.put(key,value);
    }

    @Override
    public void update(T1 key, T2 value) throws Exception
    {
        if(key==null || value==null)
            throw new NullPointerException("You cannot add null values");
        if(!this.dictionary.containsKey(key))
            throw new Exception("Key does not exist");
        this.dictionary.put(key,value);
    }

    @Override
    public T2 lookFor(T1 id) throws Exception
    {
        if(!this.dictionary.containsKey(id))
            throw new Exception("This element does not exist");
        return this.dictionary.get(id);
    }

    @Override
    public boolean isDefined(T1 id)
    {
        return this.dictionary.containsKey(id);
    }

    @Override
    public String toString()
    {
        return dictionary.toString();
    }
}


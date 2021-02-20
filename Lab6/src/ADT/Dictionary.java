package ADT;

import Model.Type.MyException;
import java.util.HashMap;
import java.util.Map;

import java.util.Hashtable;

import javax.management.openmbean.KeyAlreadyExistsException;

public class Dictionary<T1,T2> implements IDictionary<T1,T2>
{
    private Hashtable<T1,T2> dictionary;

    public Dictionary()
    {
        this.dictionary=new Hashtable<T1, T2>();
    }

    @Override
    public void add(T1 key, T2 value) throws Exception
    {
        if(key==null || value==null)
            throw new NullPointerException("You cannot add null values");
        this.dictionary.put(key,value);
    }

    @Override
    public void update(T1 key, T2 value) throws Exception
    {
        if(key==null || value==null)
            throw new NullPointerException("You cannot add null values");
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
        String dict = "";
        for(T1 k: dictionary.keySet())
        {
            dict += k.toString()+" -> "+dictionary.get(k).toString()+"   ";
        }
        return dict;
    }

    @Override
    public Map<T1, T2> getContent() {
        return dictionary;
    }

    @Override
    public IDictionary<T1, T2> clone() {
        IDictionary<T1, T2> out = new Dictionary<>();
        for(T1 k: this.dictionary.keySet())
        {
            try {
                out.add(k,this.lookFor(k));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return out;

    }

    @Override
    public Hashtable<T1, T2> getAll() {
        return dictionary;
    }

}


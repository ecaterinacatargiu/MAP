package ADT;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyList<T1> implements IList<T1>, Iterable<T1>
{
    private ArrayList<T1> list;

    public MyList()
    {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T1 elem)
    {
        list.add(elem);
    }

    @Override
    public void delete(T1 elem) throws EmptyListException
    {
        if(this.list.isEmpty())
        {
            throw new EmptyListException("The list is empty");
        }
        this.list.remove(elem);
    }

    @Override
    public int size()
    {
        return this.list.size();
    }

    @Override
    public T1 get(int index)
    {
        return this.list.get(index);
    }

    @Override
    public String toString()
    {
        return list.toString();
    }

    @Override
    public ArrayList<T1> getAll() {
        return list;
    }

    @Override
    public Iterator<T1> iterator()
    {
        return this.list.iterator();
    }
}

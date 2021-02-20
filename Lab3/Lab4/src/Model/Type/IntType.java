package Model.Type;

public class IntType implements Type
{
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof IntType)
            return true;
        else
            return false;
    }

    @Override
    public String toString()
    {
        return "int";
    }
}
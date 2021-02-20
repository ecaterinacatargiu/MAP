package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Structure.MyIDictionary;
import Model.Structure.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public class VarExp implements Exp{
    String id;

    public VarExp(String i)
    {
        id = i;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }
}

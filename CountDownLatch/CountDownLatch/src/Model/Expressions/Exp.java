package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Structure.MyIDictionary;
import Model.Structure.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public interface Exp {
    Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException;
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}

package Model.Expression;

import ADT.IDictionary;
import ADT.IHeap;
import Model.Type.MyException;
import Model.Type.Type;
import Model.Values.Value;

public interface Expression extends Cloneable{
    Value evaluate(IDictionary<String, Value> table, IHeap heap) throws Exception;

    Type typecheck(IDictionary<String, Type> typeEnv) throws Exception;

    Object clone() throws CloneNotSupportedException;


}

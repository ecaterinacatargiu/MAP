package Model.Expression;

import ADT.IDictionary;
import ADT.IHeap;
import Model.Type.MyException;
import Model.Type.Type;
import Model.Values.Value;

public interface Expression {
    Value evaluate(IDictionary<String, Value> table, IHeap heap) throws Exception;
    Type typeCheck(IDictionary<String, Type> typeEnv) throws Exception;
}

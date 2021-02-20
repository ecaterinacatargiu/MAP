package Model.Expression;

import ADT.IDictionary;
import Model.Type.MyException;
import Model.Values.Value;

public interface Expression {
    Value evaluate(IDictionary<String, Value> table) throws Exception;
}

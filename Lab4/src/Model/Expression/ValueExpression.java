package Model.Expression;

import ADT.IDictionary;
import ADT.IHeap;
import Model.Type.MyException;
import Model.Type.Type;
import Model.Values.Value;

public class ValueExpression implements Expression
{
    private Value value;

    public ValueExpression(Value value)
    {
        this.value=value;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap heap)
    {
        return value;
    }

    @Override
    public Type typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        return value.getType();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ValueExpression clone =(ValueExpression) super.clone();
        clone.value = (Value) this.value.clone();
        return clone;
    }

    @Override
    public String toString() { return value.toString(); }
}

package Model.Expression;

import ADT.IDictionary;
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
    public Value evaluate(IDictionary<String, Value> table)
    {
        return this.value;
    }

    @Override
    public String toString() { return value.toString(); }
}

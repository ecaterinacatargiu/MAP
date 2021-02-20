package Model.Expression;

import ADT.IDictionary;
import Model.Values.Value;

public class VariableExpression implements Expression
{
    private String id;

    public VariableExpression(String id)
    {
        this.id=id;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table) throws Exception {
        return table.lookFor(this.id);
    }

    @Override
    public String toString() { return id.toString();}
}

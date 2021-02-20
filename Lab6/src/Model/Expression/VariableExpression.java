package Model.Expression;

import ADT.IDictionary;
import ADT.IHeap;
import Model.Type.Type;
import Model.Values.Value;

public class VariableExpression implements Expression
{
    private String id;

    public VariableExpression(String id)
    {
        this.id=id;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap heap) throws Exception {
        return table.lookFor(id);
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        return typeEnv.lookFor(id);
    }

    @Override
    public String toString() { return id;}
}

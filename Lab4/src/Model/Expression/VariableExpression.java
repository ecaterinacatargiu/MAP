package Model.Expression;

import ADT.IDictionary;
import ADT.IHeap;
import Model.Type.MyException;
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
    public Type typecheck(IDictionary<String, Type> typeEnv) throws Exception {
        return typeEnv.lookFor(id);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        VariableExpression clone = (VariableExpression) super.clone();
        clone.id = this.id;
        return clone;
    }

    @Override
    public String toString() { return id;}
}

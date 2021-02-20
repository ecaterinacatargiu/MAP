package Model.Expression;

import ADT.IDictionary;
import ADT.IHeap;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class MULExpression implements Expression {

    Expression expr1;
    Expression expr2;

    public MULExpression(Expression e1, Expression e2)
    {
        this.expr1 = e1;
        this.expr2 = e2;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap heap) throws Exception {
        Value v1,v2;
        v1=expr1.evaluate(table,heap);
        v2=expr2.evaluate(table,heap);
        IntValue res1,res2;
        res1= (IntValue) v1;
        res2= (IntValue) v2;
        int n1,n2;
        n1 = res1.getValue();
        n2= res2.getValue();
        int resint = (n1*n2)-(n1+n2);
        return new IntValue(resint);
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        return new IntType();
    }

    @Override
    public String toString()
    {
        return "MUL("+expr1.toString()+", "+expr2.toString()+")";
    }
}

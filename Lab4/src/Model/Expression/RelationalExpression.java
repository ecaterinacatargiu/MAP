package Model.Expression;

import ADT.IDictionary;
import ADT.IHeap;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

import java.util.concurrent.ExecutionException;

public class RelationalExpression implements Expression {

    private Expression first;
    private Expression second;
    private String oper;

    public RelationalExpression(Expression expression1, Expression expression2, String s)
    {
        first = expression1;
        second = expression2;
        oper = s;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap heap) throws Exception
    {
        Value value1 = first.evaluate(table,heap);
        if(value1.getType().equals((new IntType())))
        {
            Value value2 = second.evaluate(table, heap);
            if(value2.getType().equals(new IntType()))
            {
                int n1 = ((IntValue) value1).getValue();
                int n2 = ((IntValue) value2).getValue();
                switch (oper)
                {
                    case "<":
                        if(n1<n2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);
                    case "<=":
                        if(n1<=n2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);
                    case "==":
                        if(n1==n2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);
                    case "!=":
                        if(n1!=n2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);
                    case ">":
                        if(n1>n2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);
                    case ">=":
                        if(n1>=n2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);
                    default:
                        throw new Exception("Invalid op");
                }
            }
            else throw new Exception("Invalid expression");
        }
        else throw new Exception("Invalid expression");
    }

    @Override
    public Type typecheck(IDictionary<String, Type> typeEnv) throws Exception {
            Type type1, type2;
            type1 = first.typecheck(typeEnv);
            type2 = second.typecheck(typeEnv);
            if(type1.equals(new IntType()))
            {
                if(type2.equals(new IntType()))
                {
                    return new BoolType();
                }
                else throw new Exception("Second operand is not an integer");
            }
            else throw new Exception("First operand is not an integer");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        RelationalExpression clone = (RelationalExpression) super.clone();
        clone.first = (Expression) this.first.clone();
        clone.second = (Expression) this.second.clone();
        clone.oper = this.oper;
        return clone;
    }

    @Override
    public String toString() { return first.toString() + oper + second.toString(); }
}



package Model.Expression;

import ADT.IDictionary;
import ADT.IHeap;
import Model.Type.BoolType;
import Model.Type.MyException;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

import java.nio.file.LinkOption;

public class LogicalExpression implements Expression
{
    private Expression first;
    private Expression second;
    int operation; //1&&, 2||
    String oper;

    public LogicalExpression(String oper1, Expression first1, Expression second1)
    {
        first = first1;
        second = second1;
        operation = -1;
        oper = oper1;
        if(oper.equals("&&"))
            operation=1;
        if(oper.equals("||"))
            operation=2;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap heap) throws Exception
    {
        Value value1, value2;
        value1 = first.evaluate(table, heap);
        if(value1.getType().equals(new BoolType()))
        {
            value2 = second.evaluate(table,heap);
            if(value2.getType().equals(new BoolType()))
            {
                BoolValue i1 = (BoolValue) value1;
                BoolValue i2 = (BoolValue) value2;
                Boolean b1,b2;
                b1=i1.getValue();
                b2=i2.getValue();
                if(operation==1)
                    return new BoolValue(b1 && b2);
                else if(operation==2)
                    return new BoolValue(b1 || b2);
                else
                    throw new Exception("Invalid operand");
            }
            else
                throw new Exception("Second operator is not boolean");
        }
        throw new Exception("First operator is not boolean");
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        Type typeExpr1 = first.typeCheck(typeEnv);
        Type typeExpr2 = second.typeCheck(typeEnv);
        if(typeExpr1 instanceof BoolType)
            if(typeExpr2 instanceof BoolType)
                return new BoolType();
            else throw new Exception("Second operand is not bool");
        else throw new Exception("First operand is  not bool");
    }

    @Override
    public String toString()
    {
        return first.toString() + oper + second.toString();
    }
}

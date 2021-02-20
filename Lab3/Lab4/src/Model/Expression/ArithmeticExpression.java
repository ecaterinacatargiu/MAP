package Model.Expression;

import ADT.IDictionary;
import Model.Type.IntType;
import Model.Values.Value;
import Model.Values.IntValue;

public class ArithmeticExpression implements Expression
{
    private Expression first;
    private Expression second;
    int operation; //1+2-3*4/
    String oper;

    /*public ArithmeticExpression(Expression first, Expression second, int operation)
    {
        this.first=first;
        this.second=second;
        this.operation=operation;
    }*/

    public ArithmeticExpression(String ops, Expression expression1, Expression expression2)
    {
        operation = -1;
        first = expression1;
        second = expression2;
        oper = ops;
        if(ops.equals("+"))
            operation=1;
        if(ops.equals("-"))
            operation=2;
        if(ops.equals("*"))
            operation=3;
        if(ops.equals("/"))
            operation=4;

    }

    @Override
    public Value evaluate(IDictionary<String, Value> table) throws Exception

    {
        Value value1, value2;
        value1 = first.evaluate(table);

        if(value1.getType().equals(new IntType()))
        {
            value2 = second.evaluate(table);
            if (value2.getType().equals(new IntType()))
            {
                IntValue int1 = (IntValue) value1;
                IntValue int2 = (IntValue) value2;
                int nr1, nr2;
                nr1 = int1.getValue();
                nr2 = int2.getValue();
                if(operation == 1)
                    return new IntValue(nr1+nr2);
                if(operation==2)
                    return new IntValue(nr1-nr2);
                if(operation==3)
                    return new IntValue(nr1*nr2);
                if(operation==4)
                {
                    if(nr2==0)
                        throw new Exception("Division by 0");
                    else
                        return new IntValue(nr1/nr2);
                }
                else
                    throw new Exception("Invalid operand");
            }
            else
                throw new Exception("Second operand not an int");
        }
        else throw new Exception("First operand not an int");
    }

    @Override
    public String toString()
    {
        return first.toString() + operation + second.toString();
    }
}

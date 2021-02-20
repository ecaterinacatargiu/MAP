package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Structure.MyIDictionary;
import Model.Structure.MyIHeap;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    int op; //1+ 2- 3* 4/
    String ope;

    public ArithExp(String ops, Exp e11, Exp e22)
    {
        op = -1;
        e1 = e11;
        e2 = e22;
        ope = ops;
        if(ops.equals("+"))
            op = 1;
        if(ops.equals("-"))
            op = 2;
        if(ops.equals("*"))
            op = 3;
        if(ops.equals("/"))
            op = 4;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException
    {
        Value v1, v2;
        v1 = e1.eval(tbl,hp);

        if(v1.getType().equals(new IntType()))
        {
            v2 = e2.eval(tbl,hp);
            if (v2.getType().equals(new IntType()))
            {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int nr1, nr2;
                nr1 = i1.getVal();
                nr2 = i2.getVal();
                if(op == 1)
                    return new IntValue(nr1+nr2);
                if(op==2)
                    return new IntValue(nr1-nr2);
                if(op==3)
                    return new IntValue(nr1*nr2);
                if(op==4)
                {
                    if(nr2==0)
                        throw new MyException("division by 0");
                    else
                        return new IntValue(nr1/nr2);
                }
                else
                    throw new MyException("invalid operand");
            }
            else
                throw new MyException("second operand not an int");
        }
        else throw new MyException("first operand not an int");
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type tExp1 = e1.typeCheck(typeEnv);
        Type tExp2 = e2.typeCheck(typeEnv);
        if(tExp1 instanceof IntType)
            if (tExp2 instanceof IntType)
                return new IntType();
            else throw new MyException("second operand not bool");
        else throw new MyException("first operand not bool");
    }

    @Override
    public String toString() {
        return e1.toString()+ ope + e2.toString();
    }
}

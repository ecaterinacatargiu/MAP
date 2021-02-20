package Model.Statement;

import ADT.IDictionary;
import ADT.IHeap;
import ADT.IList;
import Model.Expression.Expression;
import Model.State.ProgramState;
import Model.Type.MyException;
import Model.Type.Type;
import Model.Values.Value;

public class PrintStatement implements IStatement
{
    private Expression expression;

    public PrintStatement(Expression expression)
    {
        this.expression=expression;
    }

    @Override
    public String toString()
    {
        return "print("+expression.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception
    {
        IList<Value> list = state.getOut();
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IHeap heap = state.getHeap();

        list.add(this.expression.evaluate(symbolTable, heap));
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }
}

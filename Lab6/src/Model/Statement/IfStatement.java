package Model.Statement;

import ADT.IDictionary;
import ADT.IHeap;
import ADT.IStack;
import Model.Expression.Expression;
import Model.State.ProgramState;
import Model.Type.BoolType;
import Model.Type.MyException;
import Model.Type.Type;
import Model.Values.BoolValue;
import Model.Values.Value;
import java.sql.Ref;

import java.util.concurrent.ExecutionException;

public class IfStatement implements IStatement
{
    private Expression expression;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement)
    {
        this.expression=expression;
        this.thenStatement=thenStatement;
        this.elseStatement=elseStatement;
    }

    @Override
    public String toString()
    {
        return " IF ( " +expression.toString()+" ) "+" THEN ( "+thenStatement.toString()+" ) "+"ELSE ( "+elseStatement.toString()+" ) ";
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception
    {
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IHeap heap = state.getHeap();

        Value result = expression.evaluate(symbolTable, heap);

        if(!result.getType().equals(new BoolType())) throw new Exception("Wrong expression!");
        BoolValue boolVal = (BoolValue) result;
        boolean ok = boolVal.getValue();
        if(ok)
        {
            state.getExecutionStack().push(thenStatement);
        }
        else
        {
            state.getExecutionStack().push(elseStatement);
        }
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        if(expression.typeCheck(typeEnv).equals(new BoolType()))
        {
            thenStatement.typeCheck(typeEnv.clone());
            elseStatement.typeCheck(typeEnv.clone());
            return typeEnv;
        }
        else throw new Exception("Condition not a bool expression");
    }
}

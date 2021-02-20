package Model.Statement;

import ADT.IDictionary;
import ADT.IStack;
import Model.Expression.Expression;
import Model.State.ProgramState;
import Model.Type.MyException;
import Model.Values.Value;

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
        IStack<IStatement> executionStack = state.getExecutionStack();
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        Value result = this.expression.evaluate(symbolTable);
        if(!result.getValue().equals(0))
            executionStack.push(thenStatement);
        else
            executionStack.push(elseStatement);
        return state;
    }
}

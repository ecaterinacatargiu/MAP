package Model.Statement;

import ADT.IDictionary;
import ADT.IStack;
import Model.Expression.Expression;
import Model.State.ProgramState;
import Model.Type.MyException;
import Model.Type.Type;
import Model.Values.Value;

public class AssignmentStatement implements IStatement
{
    private String id;
    private Expression expression;

    public AssignmentStatement(String id, Expression expression)
    {
        this.id=id;
        this.expression=expression;
    }

    @Override
    public String toString()
    {
        return this.id+"="+this.expression.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IStack<IStatement> stack = state.getExecutionStack();
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        Value expressionValue = expression.evaluate(symbolTable);
        if(symbolTable.isDefined(id))
        {
            Type variableType = (symbolTable.lookFor(id)).getType();
            if(expressionValue.getType().equals(variableType))
            {
                symbolTable.update(id, expressionValue);
            }
            else throw new MyException(id, variableType, expressionValue.getType());
        }
        else throw new UndeclaredVariableException(id);
        return state;
    }
}

package Model.Statement;

import ADT.IDictionary;
import ADT.IHeap;
import ADT.IStack;
import ADT.MyHeap;
import Model.Expression.Expression;
import Model.State.ProgramState;
import Model.Type.MyException;
import Model.Type.Type;
import Model.Values.Value;
import jdk.jfr.Experimental;

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
        return this.id + "=" + this.expression.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IStack<IStatement> stack = state.getExecutionStack();
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IHeap heap = state.getHeap();

        if(symbolTable.isDefined(id))
        {
            Value expressionValue = expression.evaluate(symbolTable,heap);
            Type variableType = (symbolTable.lookFor(id)).getType();
            if(expressionValue.getType().equals(variableType))
            {
                symbolTable.update(id, expressionValue);
            }
            else throw new MyException(id, variableType, expressionValue.getType());
        }
        else throw new UndeclaredVariableException(id);
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws Exception {
        Type typeVar = typeEnv.lookFor(id);
        Type typeExpr = expression.typecheck(typeEnv);
        if(typeVar.equals(typeExpr))
            return typeEnv;
        else throw new Exception("Assignment: right hand-side and left hand-side are different types");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AssignmentStatement clone = (AssignmentStatement) super.clone();
        clone.id = this.id;
        clone.expression = (Expression) this.expression.clone();
        return clone;
    }
}

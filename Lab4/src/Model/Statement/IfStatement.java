package Model.Statement;

import ADT.Dictionary;
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
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws Exception {
        Type typeExpr = expression.typecheck(typeEnv);
        Type boolType = new BoolType();
        if(typeExpr.equals(boolType))
        {
            thenStatement.typecheck(typeEnv.clone());
            elseStatement.typecheck(clone(typeEnv));
            return typeEnv;
        }
        else throw new Exception("The condition of IF is not of boolean type");
    }
    

    @Override
    public Object clone() throws CloneNotSupportedException {
        IfStatement clone = (IfStatement) super.clone();
        clone.expression = (Expression) this.expression.clone();
        clone.thenStatement = (IStatement) this.thenStatement.clone();
        clone.elseStatement = (IStatement) this.elseStatement.clone();
        return clone;

    }
}

package Model.Statement;

import ADT.Dictionary;
import ADT.IDictionary;
import ADT.IHeap;
import Model.Expression.Expression;
import Model.State.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class WhileStatement implements IStatement {

    private Expression expression;//condition
    private IStatement statement;

    public WhileStatement(Expression expr, IStatement statement)
    {

        this.expression= expr;
        this.statement = statement;
    }


    @Override
    public ProgramState execute(ProgramState state) throws Exception
    {
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IHeap heap = state.getHeap();
        Value value = expression.evaluate(symbolTable, heap);
        if(value.getType().equals(new BoolType()))
        {
            BoolValue boolVal = (BoolValue) value;
            if(boolVal.getValue())
            {
                state.getExecutionStack().push(new WhileStatement(expression, statement));
                state.getExecutionStack().push(statement);
            }
            return null;
        }
        else throw new Exception("Invalid type");
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws Exception {
        Type typeExpression = expression.typecheck(typeEnv);
        Type boolType = new BoolType();
        if(!typeExpression.equals(boolType)) throw new Exception("NOPPEEE");
        IDictionary<String, Type> copyStatement = new Dictionary<>(typeEnv);
        statement.typecheck(copyStatement);

        return typeEnv;

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        WhileStatement clone = (WhileStatement) super.clone();
        clone.statement = (IStatement) this.statement.clone();
        clone.expression = (Expression) this.expression.clone();
        return clone;
    }

    public String toString()
    {
        return "WHILE("+expression.toString()+")";
    }
}

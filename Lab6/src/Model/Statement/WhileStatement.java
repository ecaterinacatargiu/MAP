package Model.Statement;

import ADT.IDictionary;
import ADT.IHeap;
import Model.Expression.Expression;
import Model.State.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class WhileStatement implements IStatement {

    private Expression expression;
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
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        Type typeExpr = expression.typeCheck(typeEnv);
        if(typeExpr instanceof BoolType)
        {
            statement.typeCheck(typeEnv.clone());
            return typeEnv;
        }
        else throw new Exception("While condition is not a bool expression");
    }

    public String toString()
    {
        return "WHILE("+expression.toString()+")";
    }
}

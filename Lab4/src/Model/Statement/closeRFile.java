package Model.Statement;

import ADT.IDictionary;
import ADT.IFileTable;
import ADT.IHeap;
import Model.Expression.Expression;
import Model.State.ProgramState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStatement {

    private Expression expression;

    public closeRFile(Expression expr) { expression = expr;}

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IFileTable fileTable = state.getFileTable();
        IHeap heap = state.getHeap();

        Value value = expression.evaluate(symbolTable, heap);
        if(value.getType().equals(new StringType()))
        {
            if(fileTable.isDefined((StringValue) value))
            {
                BufferedReader fd = fileTable.get((StringValue) value);
                try
                {
                    fd.close();
                    fileTable.delete((StringValue) value);
                }
                catch(IOException e)
                {
                    throw new Exception("Invalid close");
                }
            }
            else throw new Exception("Invalid filename");
        }
        else throw new Exception("Invalid filename");
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws Exception {
        Type expressionType = expression.typecheck(typeEnv);
        Type stringType = new StringType();
        if(!expressionType.equals(stringType)) throw new Exception("Not the same type");

        return typeEnv;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        closeRFile clone = (closeRFile) super.clone();
        clone.expression = (Expression) this.expression.clone();
        return clone;
    }

    @Override
    public String toString() { return "closeRfILE("+expression.toString()+")";}
}

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
import jdk.jfr.Experimental;

import java.beans.IndexedPropertyChangeEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.ExecutionException;

public class openRFile implements IStatement {

    private Expression expression; //path of the file

    public openRFile(Expression expr) { expression = expr; }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IFileTable fileTable = state.getFileTable();
        IHeap heap = state.getHeap();

        Value value = expression.evaluate(symbolTable, heap);
        if(value.getType().equals(new StringType()))
        {
            StringValue name = (StringValue) value;
            if(!fileTable.isDefined(name))
            {
                try
                {
                    BufferedReader fd = new BufferedReader((new FileReader((String) name.getValue())));
                    fileTable.add(name, fd);
                }
                catch (FileNotFoundException e)
                {
                    throw new Exception("File was not found");
                }
            }
            else throw new Exception("Name was already defined");
        }
        else throw new Exception("Invalid filename");
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws Exception {
        Type expressionType = expression.typecheck(typeEnv); //filepath
        Type stringType = new StringType();
        if(!expressionType.equals(stringType)) throw new Exception("Invaliiiid");

        return typeEnv;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        openRFile clone = (openRFile) super.clone();
        clone.expression = (Expression) this.expression.clone();
        return clone;
    }

    @Override
    public String toString() { return "openRFile("+expression.toString()+")";}

}

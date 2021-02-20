package Model.Statement;

import ADT.IDictionary;
import ADT.IFileTable;
import ADT.IHeap;
import Model.Expression.Expression;
import Model.State.ProgramState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import org.w3c.dom.css.CSSImportRule;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStatement {

    private Expression expression;
    private String var_name;

    public readFile(Expression expr, String name)
    {
        expression = expr;
        var_name = name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IDictionary<String, Value> symbolTable = state.getSymbolTable();

        if(symbolTable.isDefined(var_name))
        {
            IFileTable fileTable = state.getFileTable();
            IHeap heap = state.getHeap();

            Value value = expression.evaluate(symbolTable, heap);
            if(value.getType().equals(new StringType()))
            {
                BufferedReader fd = fileTable.get((StringValue) value);
                if(fd==null)
                    throw new Exception("Invalid fd");
                try{
                    String read = fd.readLine();
                    IntValue nr;
                    if(read == null)
                        nr = new IntValue(0);
                    else
                        nr = new IntValue(Integer.parseInt(read));
                    symbolTable.add(var_name,nr);
                }
                catch(IOException e)
                {
                    throw new Exception("Invalid reading");
                }
            }
            else throw new Exception("Invalid filename");
        }
        else throw new Exception("Invalid variable name");
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws Exception {
        Type expressionType = expression.typecheck(typeEnv);
        Type stringType = new StringType();
        if(!expressionType.equals(stringType)) throw new Exception("Invaliiiid");

        return typeEnv;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        readFile clone = (readFile) super.clone();
        clone.var_name = this.var_name;
        clone.expression = (Expression) this.expression.clone();
        return clone;
    }

    @Override
    public String toString() { return "readFile("+expression.toString()+")";}
}

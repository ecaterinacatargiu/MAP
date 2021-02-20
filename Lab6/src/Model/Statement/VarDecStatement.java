package Model.Statement;

import ADT.IDictionary;
import Model.State.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class VarDecStatement implements IStatement
{
    private String name;
    private Type type;

    public VarDecStatement(String name, Type type)
    {
        this.name=name;
        this.type=type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception
    {
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        if(symbolTable.isDefined(this.name)) throw new Exception("Variable with the same name already exists");
        symbolTable.add(name,type.defaultValue());
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        typeEnv.add(name,type);
        return typeEnv;
    }

    @Override
    public String toString() { return type.toString() + " " + name;}
}

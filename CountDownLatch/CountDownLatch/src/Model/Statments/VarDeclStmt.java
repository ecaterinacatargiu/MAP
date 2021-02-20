package Model.Statments;

import Model.Exceptions.MyException;
import Model.Structure.MyIDictionary;
import Model.Structure.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class VarDeclStmt implements IStmt{
    String id;
    Type type;

    public VarDeclStmt(String v, Type e)
    {
        id = v;
        type = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException
    {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(symTbl.isDefined(id))
            throw new MyException(id + " is already defined");
        symTbl.addToDict(id, type.defaultValue());

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.addToDict(id, type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type.toString()+" "+id;
    }
}

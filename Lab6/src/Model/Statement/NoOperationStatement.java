package Model.Statement;

import ADT.IDictionary;
import Model.State.ProgramState;
import Model.Type.Type;

public class NoOperationStatement implements IStatement
{
    public NoOperationStatement()
    {

    }

    @Override
    public ProgramState execute(ProgramState state)
    {
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        return typeEnv;
    }

    @Override
    public String toString() { return "Nope";}
}

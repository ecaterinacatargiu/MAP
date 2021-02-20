package Model.Statments;

import Model.Exceptions.MyException;
import Model.Structure.MyIDictionary;
import Model.Structure.PrgState;
import Model.Types.Type;

public class NopStmt implements IStmt{
    public NopStmt()
    {

    }

    @Override
    public PrgState execute(PrgState state)
    {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "NOP";
    }
}

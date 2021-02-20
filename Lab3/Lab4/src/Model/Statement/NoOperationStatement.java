package Model.Statement;

import Model.State.ProgramState;

public class NoOperationStatement implements IStatement
{
    @Override
    public ProgramState execute(ProgramState state)
    {
        return null;
    }

    @Override
    public String toString() { return "Nope";}
}

package Model.Statement;

import ADT.IStack;
import Model.State.ProgramState;

public class CompoundStatement implements IStatement
{
    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement first, IStatement second)
    {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString()
    {
        return "("+first.toString()+";"+second.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState state)
    {
        IStack<IStatement> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return state;
    }

}

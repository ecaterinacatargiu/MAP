package Model.Statement;

import ADT.IDictionary;
import ADT.IStack;
import Model.State.ProgramState;
import Model.Type.Type;

public class CompoundStatement implements IStatement
{
    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement f, IStatement s)
    {
        first = f;
        second = s;
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
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        return second.typeCheck(first.typeCheck(typeEnv));
    }

}

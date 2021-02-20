package Model.Statement;

import ADT.IDictionary;
import ADT.IStack;
import Model.State.ProgramState;
import Model.Type.Type;
import Model.Values.Value;

public class SleepStatement implements IStatement {

    private Integer value;

    public SleepStatement(Integer value)
    {
        this.value = value;
    }


    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        if(value!=0)
        {
            IStack<IStatement> execStack = state.getExecutionStack();
            execStack.push(new SleepStatement(value-1));
            state.setExecutionStack(execStack);
        }
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        return null;
    }

    @Override
    public String toString()
    {
        return "sleep("+value.toString()+")";
    }
}

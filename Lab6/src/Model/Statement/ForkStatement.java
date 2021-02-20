package Model.Statement;

import ADT.IDictionary;
import ADT.MyStack;
import Model.State.ProgramState;
import Model.Type.Type;

public class ForkStatement implements IStatement {

    IStatement statement;

    public ForkStatement(IStatement statement)
    {
        this.statement = statement;
    }


    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        ProgramState.setId();
        ProgramState newState = new ProgramState(new MyStack<IStatement>(), state.cloneSymbolTable(), state.getOut(), state.getFileTable(), state.getHeap(), statement, state.getLatchTable());
        return newState;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        statement.typeCheck(typeEnv.clone());
        return typeEnv;
    }

    @Override
    public String toString()
    {
        return "fork("+statement.toString()+")";
    }
}

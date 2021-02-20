package Model.Statement;

import ADT.Dictionary;
import ADT.IDictionary;
import ADT.MyStack;
import Model.State.ProgramState;
import Model.Type.Type;

import java.util.Hashtable;

public class ForkStatement implements IStatement {

    IStatement statement;

    public ForkStatement(IStatement statement)
    {
        this.statement = statement;
    }


    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        ProgramState.setId();
        ProgramState newState = new ProgramState(new MyStack<IStatement>(), state.cloneSymbolTable(), state.getOut(), state.getFileTable(), state.getHeap(), statement);
        return newState;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws Exception {
        IDictionary<String, Type> copyInner = new Dictionary<> (typeEnv);
        statement.typecheck(copyInner);

        return typeEnv;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ForkStatement clone = (ForkStatement) super.clone();
        clone.statement = (IStatement) statement.clone();
        return clone;
    }

    @Override
    public String toString()
    {
        return "fork("+statement.toString()+")";
    }
}

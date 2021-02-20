package Model.Statments;

import Model.Exceptions.MyException;
import Model.Structure.MyIDictionary;
import Model.Structure.MyStack;
import Model.Structure.PrgState;
import Model.Types.Type;

public class forkStmt implements IStmt {

    IStmt stmt;

    public forkStmt(IStmt st)
    {
        stmt = st;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        PrgState.setId();
        PrgState newState = new PrgState(new MyStack<IStmt>(), state.cloneSymTbl(), state.getOut(), state.getFileTable(), state.getHeap(), state.getLatchTable(), stmt);
        return newState;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        stmt.typeCheck(typeEnv.clone());
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork("+stmt.toString()+")";
    }
}

package Model.Statments;

import Model.Exceptions.MyException;
import Model.Structure.MyIDictionary;
import Model.Structure.MyIStack;
import Model.Structure.PrgState;
import Model.Types.Type;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt second;


    public CompStmt(IStmt f, IStmt s)
    {
        first = f;
        second = s;
    }

    @Override
    public PrgState execute(PrgState state)
    {
        MyIStack<IStmt> stk = state.getExeStack();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return second.typeCheck(first.typeCheck(typeEnv));
    }

    @Override
    public String toString() {
        return first.toString()+";"+second.toString();
    }
}

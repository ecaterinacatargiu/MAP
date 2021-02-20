package Model.Statments;

import Model.Exceptions.MyException;
import Model.Structure.MyIDictionary;
import Model.Structure.MyILatch;
import Model.Structure.PrgState;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class awaitStmt implements IStmt{
    String var;

    public awaitStmt(String var) {
        this.var = var;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(symTbl.isDefined(var)) {
            MyILatch latchTable = state.getLatchTable();
            int foundIndex = ((IntValue)symTbl.lookup(var)).getVal();
            synchronized (latchTable) {
                if (latchTable.isDefined(foundIndex)) {
                    if (latchTable.lookup(foundIndex) != 0)
                        state.getExeStack().push(new awaitStmt(var));
                    return null;
                } else throw new MyException("Invalid found index");
            }
        }
        else throw new MyException("Invalid var name");
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "await("+var+")";
    }
}

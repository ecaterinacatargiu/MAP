package Model.Statments;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Structure.MyIDictionary;
import Model.Structure.MyILatch;
import Model.Structure.PrgState;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class newLatch implements IStmt {

    String var;
    Exp exp;

    public newLatch(String var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        int res = ((IntValue) exp.eval(state.getSymTable(), state.getHeap())).getVal();
        MyILatch latchTable = state.getLatchTable();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        synchronized (latchTable)
        {
            int key = latchTable.addToLatch(res);
            if(symTbl.isDefined(var))
                symTbl.update(var, new IntValue(key));
            else
                symTbl.addToDict(var, new IntValue(key));
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "newLatch("+var+", "+exp.toString()+")";
    }
}

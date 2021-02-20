package Model.Statments;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Structure.MyIDictionary;
import Model.Structure.MyIHeap;
import Model.Structure.MyIList;
import Model.Structure.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class PrintStmt implements IStmt{

    Exp exp;


    public PrintStmt(Exp e)
    {
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> out = state.getOut();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap heap = state.getHeap();
        out.addToList(exp.eval(symTbl, heap));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print ("+exp.toString()+")";
    }
}

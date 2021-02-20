package Model.Statement;

import ADT.IDictionary;
import ADT.ILatchTable;
import Model.State.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class AwaitStatement implements IStatement {

    private String variable;

    public AwaitStatement(String var)
    {
        this.variable=var;
    }



    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        IDictionary<String, Value> symTbl = state.getSymbolTable();
        if(symTbl.isDefined(variable)) {
            ILatchTable latchTable = state.getLatchTable();
            int foundIndex = ((IntValue)symTbl.lookFor(variable)).getValue();
            synchronized (latchTable) {
                if (latchTable.isDefined(foundIndex)) {
                    if (latchTable.lookup(foundIndex) != 0)
                        state.getExecutionStack().push(new AwaitStatement(variable));
                    return null;
                } else throw new Exception("Invalid found index");
            }
        }
        else throw new Exception("Invalid var name");
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        /*Type typeVar = typeEnv.lookFor(variable);
        if(typeVar instanceof IntType)
            return typeEnv;
        else throw new Exception("Awaiiiit");*/

        return typeEnv;
    }

    @Override
    public String toString()
    {
        return "Await("+this.variable+")";
    }
}

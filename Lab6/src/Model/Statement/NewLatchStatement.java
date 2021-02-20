package Model.Statement;

import ADT.IDictionary;
import ADT.ILatchTable;
import Model.Expression.Expression;
import Model.State.ProgramState;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class NewLatchStatement implements IStatement {

    private String variable;
    private Expression expr;

    public NewLatchStatement(String var, Expression ex)
    {
        this.variable=var;
        this.expr=ex;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        int res = ((IntValue) expr.evaluate(state.getSymbolTable(), state.getHeap())).getValue();
        ILatchTable latchTable = state.getLatchTable();
        IDictionary<String, Value> symTbl = state.getSymbolTable();
        synchronized (latchTable)
        {
            int key = latchTable.addToLatch(res);
            if(symTbl.isDefined(variable))
                symTbl.update(variable, new IntValue(key));
            else
                symTbl.add(variable, new IntValue(key));
        }
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        /*Type typeVar = typeEnv.lookFor(variable);
        Type typeExpr2 = expr.typeCheck(typeEnv);
        if(typeVar instanceof IntType)
            if(typeExpr2 instanceof IntType)
                return typeEnv;*/

        return typeEnv;
    }

    @Override
    public String toString()
    {
        return "newLatch(" + this.variable + ", " + this.expr.toString() + ")";
    }
}

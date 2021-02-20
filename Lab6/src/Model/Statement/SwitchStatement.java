package Model.Statement;

import ADT.IDictionary;
import ADT.IHeap;
import ADT.IStack;
import Model.Expression.Expression;
import Model.Expression.RelationalExpression;
import Model.State.ProgramState;
import Model.Type.Type;
import Model.Values.Value;

public class SwitchStatement implements IStatement {

    private Expression conditionE;
    private Expression case1E1;
    private IStatement statement1S1;
    private Expression case2E2;
    private IStatement statement2S2;
    //default
    private IStatement statement3S3;


    public SwitchStatement(Expression condition, Expression case1, IStatement statement1, Expression case2, IStatement statement2, IStatement statement3)
    {
        this.conditionE= condition;
        this.case1E1 = case1;
        this.statement1S1 = statement1;
        this.case2E2 = case2;
        this.statement2S2 = statement2;
        this.statement3S3 = statement3;
    }


    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IStatement st = new IfStatement(new RelationalExpression(conditionE, case1E1,"=="), statement1S1, new IfStatement(new RelationalExpression(conditionE,case2E2,"=="), statement2S2, statement3S3));
        state.getExecutionStack().push(st);
        return null;

    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        return typeEnv;
    }

    @Override
    public String toString()
    {
        return "Switch("+conditionE.toString()+")(case "+case1E1.toString()+": "+statement1S1.toString()+
                ")(case "+case2E2.toString()+": "+statement2S2.toString()+"(default: "+statement3S3.toString()+")";

    }
}

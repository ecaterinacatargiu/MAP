package Model.Statement;

import ADT.IDictionary;
import ADT.IStack;
import Model.Expression.Expression;
import Model.Expression.RelationalExpression;
import Model.Expression.VariableExpression;
import Model.State.ProgramState;
import Model.Type.Type;

public class ForStatement implements IStatement {

    private Expression initialiationE1;
    private Expression conditionE2;
    private Expression incrementE3;
    private IStatement statement;

    public ForStatement(Expression initialiation, Expression condidion, Expression increment, IStatement statement)
    {
        this.initialiationE1 = initialiation;
        this.conditionE2 = condidion;
        this.incrementE3 = increment;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IStatement st =  new CompoundStatement(new AssignmentStatement("v",initialiationE1),new WhileStatement(new RelationalExpression(new VariableExpression("v"),conditionE2,"<"),new CompoundStatement(statement,new AssignmentStatement("v",incrementE3))));
        state.getExecutionStack().push(st);
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "for(v=" + initialiationE1.toString() +",v<" + conditionE2.toString() +",v="+incrementE3.toString() + " )"+ statement.toString() ;
    }
}

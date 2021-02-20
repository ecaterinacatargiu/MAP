package Model.Statement;

import ADT.IDictionary;
import Model.Expression.ArithmeticExpression;
import Model.Expression.Expression;
import Model.Expression.ValueExpression;
import Model.State.ProgramState;
import Model.Type.Type;
import Model.Values.IntValue;

public class WaitStatement implements IStatement {

    Expression number;

    public WaitStatement(Expression nr)
    {
        this.number=nr;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        if(!(number.evaluate(state.getSymbolTable(),state.getHeap()).equals(new IntValue(0)))){
            IStatement st = new CompoundStatement(new PrintStatement(number),new WaitStatement(new ArithmeticExpression("-",number,new ValueExpression(new IntValue(1)))));
            state.getExecutionStack().push(st);
        }
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        return typeEnv;
    }

    @Override
    public String toString(){
        return "wait("+ String.valueOf(number)+")";

    }
}

package Model.Statement;

import ADT.Dictionary;
import ADT.IDictionary;
import ADT.IHeap;
import ADT.MyHeap;
import Model.Expression.Expression;
import Model.State.ProgramState;
import Model.Type.Type;
import Model.Values.ReferenceValue;
import Model.Values.Value;
import Model.Type.ReferenceType;

import javax.swing.plaf.synth.SynthMenuBarUI;

public class WriteHeapStatement implements IStatement {

    private String variableName;
    private Expression expression;

    public WriteHeapStatement(String variable, Expression expression)
    {
        this.variableName= variable;
        this.expression=expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        IDictionary<String, Value> symmbolTable = state.getSymbolTable();
        IHeap heap = state.getHeap();
        if(symmbolTable.isDefined(variableName))
        {
            Value value = symmbolTable.lookFor(variableName);
            if(value.getType() instanceof ReferenceType)
            {
                ReferenceValue refValue = (ReferenceValue) value;
                if(heap.isAddress(refValue.getAddress()))
                {
                    Value evalValue = expression.evaluate(symmbolTable,heap);
                    if(evalValue.getType().equals(((ReferenceType)refValue.getType()).getInnerType()))
                    {
                        heap.updateHeap(refValue.getAddress(),evalValue);
                        return null;
                    }
                    else throw new Exception("Invalid type");
                }
                else throw new Exception("Invalid address");
            }
            else throw new Exception("Invalid type");
        }
        else throw new Exception("Variable not defined");
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws Exception {
        Type typeVar = typeEnv.lookFor(variableName);
        Type typeExpr = expression.typecheck(typeEnv);
        Type expectedType = new ReferenceType(typeExpr);
        if(!typeVar.equals(expectedType)) throw new Exception("Invalid typess");
        return typeEnv;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        WriteHeapStatement clone = (WriteHeapStatement) super.clone();
        clone.variableName = variableName;
        clone.expression = (Expression) this.expression.clone();
        return null;
    }

    @Override
    public String toString() { return "wH("+variableName+", "+expression.toString()+")";}
}

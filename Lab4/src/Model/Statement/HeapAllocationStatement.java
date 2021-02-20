package Model.Statement;

import ADT.IDictionary;
import ADT.IHeap;
import Model.Expression.Expression;
import Model.State.ProgramState;
import Model.Type.ReferenceType;
import Model.Type.Type;
import Model.Values.ReferenceValue;
import Model.Values.Value;
import com.sun.jdi.request.ExceptionRequest;

import java.rmi.server.ExportException;

public class HeapAllocationStatement implements IStatement {

    private String variableName;
    private Expression expression;

    public HeapAllocationStatement(String variableName, Expression expr){
        this.variableName = variableName;
        this.expression = expr;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IHeap heap = state.getHeap();

        if(symbolTable.isDefined(variableName))
        {
            if(symbolTable.lookFor(variableName).getType() instanceof ReferenceType)
            {
                Value value = expression.evaluate(symbolTable, heap);
                ReferenceType type = (ReferenceType) symbolTable.lookFor(variableName).getType();
                if(value.getType().equals(type.getInnerType()))
                {
                    heap.addToHeap(value);
                    //symbolTable.update(variableName, new ReferenceValue(value.getType(), heap.getCurrentFreeAddress()));
                    symbolTable.add(variableName, new ReferenceValue(value.getType(), heap.getCurrentFreeAddress()));
                    return null;
                }
                else throw new Exception("Invalid type");
            }
            else throw new Exception("Not a reference type");
        }
        else throw new Exception("Variable is not defined");
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws Exception {
        Type typeVar = typeEnv.lookFor(variableName);
        Type typeExpr = expression.typecheck(typeEnv);
        Type expectedType = new ReferenceType(typeExpr);
        if(!typeVar.equals(expectedType)) throw new Exception("Invalid types");
        return typeEnv;

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        HeapAllocationStatement clone = (HeapAllocationStatement) super.clone();
        clone.variableName = variableName;
        clone.expression = (Expression) this.expression.clone();
        return clone;
    }

    @Override
    public String toString() { return "new("+variableName+", "+expression.toString()+")";}
}

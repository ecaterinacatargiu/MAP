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
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        Type typeVar = typeEnv.lookFor(variableName);
        Type typeExpr = expression.typeCheck(typeEnv);
        if(typeVar.equals(new ReferenceType(typeExpr)))
            return typeEnv;
        else throw new Exception("New statement: different types");
    }

    @Override
    public String toString() { return "new("+variableName+", "+expression.toString()+")";}
}

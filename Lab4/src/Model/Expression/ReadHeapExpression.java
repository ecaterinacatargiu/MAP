package Model.Expression;

import ADT.IDictionary;
import ADT.IHeap;
import Model.Type.ReferenceType;
import Model.Type.Type;
import Model.Values.ReferenceValue;
import Model.Values.Value;

public class ReadHeapExpression implements Expression {

    private Expression expression;

    public ReadHeapExpression(Expression expression) { this.expression = expression; }

    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap heap) throws Exception{

        Value result = expression.evaluate(table,heap);
        if(result instanceof ReferenceValue)
        {
            ReferenceValue refValue = (ReferenceValue) result;
            int address = refValue.getAddress();
            if(heap.isAddress(address))
            {
                return heap.getValue(address);
            }
            else throw new Exception("Invalid address");
        }
        else throw new Exception("Invalid type");
    }

    @Override
    public Type typecheck(IDictionary<String, Type> typeEnv) throws Exception {
        Type type1 = expression.typecheck(typeEnv);
        if(type1 instanceof ReferenceType)
        {
            ReferenceType refType = (ReferenceType) type1;
            return refType.getInnerType();
        }
        else throw new Exception("The read heap expression is not a reference type");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ReadHeapExpression clone = (ReadHeapExpression) super.clone();
        clone.expression = (Expression) expression.clone();

        return clone;
    }

    @Override
    public String toString() { return "rH("+expression.toString()+")"; }
}

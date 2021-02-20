package Model.Statement;

import ADT.IDictionary;
import Model.Expression.Expression;
import Model.Expression.RelationalExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.State.ProgramState;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import javafx.scene.control.skin.CellSkinBase;

public class ConditionalAssignemntStatement implements IStatement{

    String varName;
    Expression expr1;
    Expression expr2;
    Expression expr3;

   public ConditionalAssignemntStatement(Expression ex1, Expression ex2, Expression ex3, String var)
   {
       this.expr1=ex1;
       this.expr2=ex2;
       this.expr3=ex3;
       this.varName=var;

   }


    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IStatement st = new IfStatement(expr1,new AssignmentStatement(varName,expr2),new AssignmentStatement(varName,expr3));
        state.getExecutionStack().push(st);
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception {
        /*Type typeExpr = expr1.typeCheck(typeEnv);
        if(typeEnv instanceof BoolType)
        {
            Type typeExpr1 = expr2.typeCheck(typeEnv);
            Type typeExpr2 = expr3.typeCheck(typeEnv);
            if(typeExpr1.equals(typeExpr2))
                return typeEnv;
            else
                throw new Exception("Operands are not of the same type.");
        }
        else throw new Exception("Operands are not of the same type");*/
        return typeEnv;

    }

    @Override
    public String toString()
    {
        return varName+"="+expr1.toString()+"?"+expr2.toString()+":"+expr3.toString();
    }








}

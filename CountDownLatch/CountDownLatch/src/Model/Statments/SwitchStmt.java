package Model.Statments;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Expressions.RelExp;
import Model.Statments.IStmt;
import Model.Statments.IfStmt;
import Model.Structure.MyIDictionary;
import Model.Structure.PrgState;
import Model.Types.Type;

public class SwitchStmt implements IStmt {
    Exp exp, exp1, exp2;
    IStmt stmt1, stmt2, stmt3;

    public SwitchStmt(Exp exp, Exp exp1, Exp exp2, IStmt stmt1, IStmt stmt2, IStmt stmt3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStmt st = new IfStmt(new RelExp(exp, exp1,"=="), stmt1, new IfStmt(new RelExp(exp,exp2,"=="), stmt2, stmt3));
        state.getExeStack().push(st);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if(exp.typeCheck(typeEnv).equals(exp1.typeCheck(typeEnv)) && exp.typeCheck(typeEnv).equals(exp2.typeCheck(typeEnv)))
        {
            stmt1.typeCheck(typeEnv.clone());
            stmt2.typeCheck(typeEnv.clone());
            stmt3.typeCheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new MyException("Different types for expressions!");
    }

    @Override
    public String toString() {
        return "Switch("+exp.toString()+")(case "+exp1.toString()+": "+stmt1.toString()+
                ")(case "+exp2.toString()+": "+stmt2.toString()+"(default: "+stmt3.toString()+")";
    }
}

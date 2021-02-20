package Model.Statments;

import Model.Exceptions.MyException;
import Model.Structure.MyIDictionary;
import Model.Structure.PrgState;
import Model.Types.Type;

public interface IStmt {
    PrgState execute (PrgState state) throws MyException;
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException;

}

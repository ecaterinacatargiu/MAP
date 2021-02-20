package Model.Statement;

import ADT.IDictionary;
import Model.State.ProgramState;
import Model.Type.Type;

public interface IStatement {
    ProgramState execute(ProgramState state) throws Exception;
    IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws Exception;
}

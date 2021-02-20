package Model.Statement;

import ADT.IDictionary;
import Model.State.ProgramState;
import Model.Type.Type;

public interface IStatement extends Cloneable {
    ProgramState execute(ProgramState state) throws Exception;

    IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws Exception;

    Object clone() throws CloneNotSupportedException;
}

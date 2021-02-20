package Model.Statement;

import Model.State.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws Exception;
}

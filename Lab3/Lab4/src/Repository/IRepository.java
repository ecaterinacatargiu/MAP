package Repository;

import Model.State.ProgramState;

public interface IRepository {
    ProgramState getCurrentProgram();
    public void addProgram(ProgramState state);
}

package Controller;

import Model.State.ProgramState;

public interface IController
{
    ProgramState oneStep(ProgramState state) throws Exception;
    void allSteps() throws Exception;
}

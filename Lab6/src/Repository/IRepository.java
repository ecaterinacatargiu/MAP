package Repository;

import Model.State.ProgramState;

import java.util.List;

public interface IRepository {

    //ProgramState getCurrentProgram();
    public void addProgram(ProgramState state);
    void logPrgStateExec(ProgramState state) throws Exception;
    List<ProgramState> getProgramsList();
    void setProgramList(List<ProgramState> listState);

    List<Integer> getListIds();
    ProgramState getProgramById(int id);

}

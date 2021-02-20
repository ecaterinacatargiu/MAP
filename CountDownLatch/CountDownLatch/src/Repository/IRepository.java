package Repository;

import Model.Exceptions.MyException;
import Model.Structure.PrgState;

import java.util.List;

public interface IRepository {

    void addState(PrgState state);

    void logPrgStateExec(PrgState state) throws MyException;

    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> listState);

    List<Integer> getListOfIds();

    PrgState getPrgStateById(int id);


}

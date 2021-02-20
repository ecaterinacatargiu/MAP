package Repository;

import Model.Exceptions.MyException;
import Model.Structure.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {

    private List<PrgState> states;
    private String logFilePath;

    public Repository(String lgPath)
    {
        states = new ArrayList<PrgState>();
        logFilePath = lgPath;
    }

    @Override
    public void addState(PrgState state)
    {
        states.add(state);
    }

    @Override
    public void logPrgStateExec(PrgState state) throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(
                    new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.append(state.toString());
            logFile.close();
        }
        catch (IOException e)
        {
            throw new MyException("file doesn't exist!");
        }
    }

    @Override
    public List<PrgState> getPrgList() {
        return states;
    }


    @Override
    public void setPrgList(List<PrgState> listState) {
        states = listState;
    }

    @Override
    public List<Integer> getListOfIds() {
        List<Integer> ids = new ArrayList<>();
        for(PrgState s: states)
            ids.add(s.getPrgId());
        return ids;
    }

    @Override
    public PrgState getPrgStateById(int id) {
        for(PrgState s: states) {
            if (s.getPrgId() == id)
                return s;
        }
        return  null;
    }

}

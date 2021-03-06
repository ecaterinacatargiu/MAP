package Repository;

import Model.State.ProgramState;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Repository implements IRepository
{
    private List<ProgramState> states;
    private String logFilePath;

    public Repository(String lgPath)
    {
        this.states=new ArrayList<ProgramState>();
        this.logFilePath = lgPath;
    }

    @Override
    public void addProgram(ProgramState state)
    {
        states.add(state);
    }

    @Override
    public void logPrgStateExec(ProgramState state) throws Exception {
        try{

            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.append((states.toString()));
            logFile.close();
        }
        catch(IOException e)
        {
            System.out.println("NOPEEEE");
        }
    }

    @Override
    public List<ProgramState> getProgramsList() {
        return states;
    }

    @Override
    public void setProgramList(List<ProgramState> listState) {
        this.states=listState;

    }

    @Override
    public List<Integer> getListIds() {
        List<Integer> listIds = new ArrayList<>();
        for(ProgramState s: states)
            listIds.add(s.getProgramId());
        return listIds;
    }

    @Override
    public ProgramState getProgramById(int id) {
        for(ProgramState s: states)
        {
            if(s.getProgramId()==id)
                return s;
        }
        return null;

    }

}

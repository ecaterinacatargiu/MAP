package Repository;

import Model.State.ProgramState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Repository implements IRepository
{
    private ArrayList<ProgramState> states;

    public Repository()
    {
        this.states=new ArrayList<ProgramState>();
    }

    @Override
    public ProgramState getCurrentProgram()
    {
        return states.get(0);
    }

    @Override
    public void addProgram(ProgramState state)
    {
        states.add(state);
    }

}

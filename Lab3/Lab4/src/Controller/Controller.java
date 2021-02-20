package Controller;

import ADT.IStack;
import Model.State.ProgramState;
import Model.Statement.IStatement;
import Model.Statement.StatementExecutionException;
import Repository.IRepository;

public class Controller implements IController
{
    private IRepository repository;

    public Controller(IRepository repository)
    {
        this.repository=repository;
    }

    @Override
    public ProgramState oneStep(ProgramState state) throws Exception
    {
        IStack<IStatement> stack = state.getExecutionStack();
        if(stack.isEmpty()) throw new StatementExecutionException();
        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    @Override
    public void allSteps() throws Exception {
        ProgramState currentProgram = this.repository.getCurrentProgram();
        System.out.println(currentProgram);
        System.out.println("------------------------------");
        while(!currentProgram.getExecutionStack().isEmpty())
        {
            oneStep(currentProgram);
            System.out.println(currentProgram);
            System.out.println("-------------------------------");
        }

    }
}

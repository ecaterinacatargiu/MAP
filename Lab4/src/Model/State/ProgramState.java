package Model.State;

import ADT.*;
import Model.Statement.IStatement;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.util.HashMap;

public class ProgramState {

    private IStack<IStatement> executionStack;
    private IDictionary<String, Value> symbolTable;
    private IList<Value> out;
    private IFileTable<StringValue, BufferedReader> fileTable;
    private IHeap heap;
    private IStatement originalProgram;
    static int id = 1;
    int programId;


    public ProgramState(IStack<IStatement> executionStack, IDictionary<String, Value> symbolTable, IList<Value> out, IFileTable fileTbl, IHeap heap, IStatement program) throws Exception {
        program.typecheck(new Dictionary<>());
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.out = out;
        this.fileTable = fileTbl;
        this.heap=heap;
        this.programId=id;
        executionStack.push(program);
    }

    @Override
    public String toString()
    {
        return "ID:" + String.valueOf(programId)+ "\nExecutionStack: " + executionStack.toString() + "\nSymbolTable: " + symbolTable.toString() + "\nOutput: " + out.toString() + "\nFileTable: " + fileTable.toString()+"\nHeap: "+ heap.toString() +"------------------\n";
    }

    public IStack<IStatement> getExecutionStack()
    {
        return this.executionStack;
    }
    public void setExecutionStack(IStack<IStatement> executionStack) { this.executionStack = executionStack; }

    public IDictionary<String, Value> getSymbolTable()
    {
        return this.symbolTable;
    }
    public void setSymbolTable(IDictionary<String, Value> dictionary) { symbolTable = dictionary; }

    public IList<Value> getOut()
    {
        return this.out;
    }
    public void setOut(IList<Value> out) { this.out = out; }

    public IFileTable getFileTable() { return this.fileTable; }
    public void setFileTable(IFileTable fileTabl) { fileTable = fileTabl; }

    public IHeap getHeap() { return this.heap; }
    public void setHeap(IHeap newHeap) { this.heap = newHeap; }

    public IDictionary<String, Value> cloneSymbolTable() throws Exception {
        IDictionary<String, Value> cloneSymbolTable = new Dictionary<>();
        for(String key: symbolTable.getContent().keySet())
        {
            cloneSymbolTable.add(key, symbolTable.lookFor(key));
        }
        return cloneSymbolTable;
    }

    public boolean isNotCompleted()
    {
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws Exception
    {
        if(executionStack.isEmpty())
            throw new Exception("Execution stack is empty!");
        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public static synchronized void setId()
    {
        id++;
    }

    public ProgramState get()
    {
        return this;
    }

}

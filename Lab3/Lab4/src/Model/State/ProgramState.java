package Model.State;

import ADT.IDictionary;
import ADT.IList;
import ADT.IStack;
import Model.Statement.IStatement;
import Model.Values.Value;

public class ProgramState {

    private IStack<IStatement> executionStack;
    private IDictionary<String, Value> symbolTable;
    private IList<Value> out;
    private IStatement originalProgram;

    public ProgramState(IStack<IStatement> executionStack, IDictionary<String, Value> symbolTable, IList<Value> out, IStatement program)
    {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.out = out;
        this.originalProgram = program;
        this.executionStack.push(this.originalProgram);
    }

    @Override
    public String toString()
    {
        return "ExecutionStack: " + executionStack.toString() + "\nSymbolTable: " + symbolTable.toString() + "\nOutput: " + out.toString();
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
}

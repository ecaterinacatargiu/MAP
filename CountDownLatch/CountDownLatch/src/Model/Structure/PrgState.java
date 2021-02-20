package Model.Structure;

import Model.Exceptions.MyException;
import Model.Statments.IStmt;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIFileTable<StringValue, BufferedReader> fileTable;
    MyIHeap heap;
    MyILatch latchTable;
    IStmt originalProgram; //optional field, but good to have
    static int id = 1;
    int prgId;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> table, MyIList<Value> ot, MyIFileTable<StringValue, BufferedReader>  fileTbl, MyIHeap hp, MyILatch latch, IStmt stmt)
    {
        exeStack=stk;
        symTable=table;
        out = ot;
        fileTable = fileTbl;
        heap = hp;
        prgId = id;
        latchTable = latch;
        //originalProgram=deepCopy(prg);//recreate the entire original prg
        stk.push(stmt);
    }

    public int getPrgId()
    {
        return prgId;
    }

    public MyIStack<IStmt> getExeStack()
    {
        return exeStack;
    }

    public void setFileTable(MyIFileTable<StringValue, BufferedReader>  ftbl) {fileTable = ftbl;}

    public MyIFileTable<StringValue, BufferedReader>  getFileTable() {return fileTable;}

    public void setExeStack(MyIStack<IStmt> stk)
    {
        exeStack = stk;
    }

    public MyIDictionary<String, Value> getSymTable()
    {
        return symTable;
    }

    public void setSymTable (MyIDictionary<String, Value> dict)
    {
        symTable = dict;
    }

    public MyIList<Value> getOut()
    {
        return out;
    }

    public void setOut(MyIList<Value> ot)
    {
        out = ot;
    }

    public MyIDictionary<String, Value> cloneSymTbl()
    {
        MyIDictionary<String, Value> symTblClone = new MyDictionary<>();
        for(String key : symTable.getContent().keySet())
        {
            symTblClone.addToDict(key, symTable.lookup(key));
        }
        return symTblClone;
    }

    public MyIHeap getHeap()
    {
        return heap;
    }

    public void setHeap(MyIHeap h)
    {
        heap = h;
    }

    @Override
    public String toString() {
        return "Id=" + String.valueOf(prgId) + "\nExeStack:\n" + exeStack.toString()
                +"SymTable_"+String.valueOf(prgId)+":\n"+ symTable.toString()+"Output:\n"+out.toString()+
                "FileTable:\n"+fileTable.toString()+"Heap:\n"+heap.toString()+
                "LatchTable:\n"+latchTable.toString()+"-------------------------------\n";
    }

    public boolean isNotCompleted()
    {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException
    {
        if(exeStack.isEmpty())
            throw new MyException("exe stack is empty!");
        IStmt currentStmt = exeStack.pop();
        return currentStmt.execute(this);
    }

    public static synchronized void setId()
    {
        id++;
    }

    public PrgState get() {
        return this;
    }

    public MyILatch getLatchTable() {
        return latchTable;
    }

    public void setLatchTable(MyILatch latchTable) {
        this.latchTable = latchTable;
    }
}


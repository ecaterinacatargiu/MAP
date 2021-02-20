package ADT;

import Model.Statement.IStatement;

import java.util.ArrayList;

public class ProcedureTable {

    private ArrayList<String> variableName;
    private IStatement body;

    public ProcedureTable(ArrayList<String> varName, IStatement bdy)
    {
        this.variableName = varName;
        this.body = bdy;
    }

    public String toString()
    {
        return "("+variableName.toString()+") { "+ body.toString() + " }";
    }
}

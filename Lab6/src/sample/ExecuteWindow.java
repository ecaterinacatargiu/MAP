package sample;


import ADT.*;
import Controller.Controller;
import Model.Expression.*;
import Model.State.ProgramState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.ReferenceType;
import Model.Type.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Utils.HeapObject;
import sample.Utils.LatchTableObj;
import sample.Utils.SymbolTableObject;

import javax.print.DocFlavor;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class ExecuteWindow {

    private IStatement statement;

    @FXML
    private
    ListView<String> prgView;
    @FXML
    private
    ListView<String> exeStackView;
    @FXML
    private
    ListView<String> outView;
    @FXML
    private
    ListView<String> fileTblView;
    @FXML
    private
    TableView<HeapObject> heapView;
    @FXML
    private
    TableView<LatchTableObj> latchView;
    @FXML
    private
    TableColumn<HeapObject, Integer> heapAddr;

    @FXML
    private
    TableColumn<HeapObject, Value> heapValue;
    @FXML
    private
    TableView<SymbolTableObject> symTblView;
    @FXML
    private
    TableColumn<SymbolTableObject, String> symName;
    @FXML
    private
    TableColumn<SymbolTableObject, Value> symValue;
    @FXML
    private
    Label stmtName;
    @FXML
    private
    Label numPrgStates;
    @FXML
    private
    TableColumn<SymbolTableObject, String> locationColumn;
    @FXML
    private
    TableColumn<SymbolTableObject, String> valueId;
    @FXML
    private
    Button runButton;

    private IDictionary<String, Value> symbolTabl;
    IHeap heap;
    private IStack<IStatement> executionStack;
    private ILatchTable latchTable;
    private ILockTable<Integer,Integer> lockTable;
    IList<Value> out;
    IFileTable<StringValue, BufferedReader> fileTable;
    ArrayList<Integer> prgListIds;
    private Controller controller;
    int size;


    public ExecuteWindow(IStatement stmt)
    {
        this.statement= stmt;
    }

    @FXML
    public void initialize() throws Exception {
        stmtName.setText(statement.toString());
        ProgramState state = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), new MyFileTable<>(), new MyHeap(), statement, new LatchTable());
        IRepository repository = new Repository("D:\\An II\\MAP\\Lab6MAPGUIFinal\\repo.txt");
        repository.addProgram(state);
        controller = new Controller(repository);

        initializeProgramStates();

        prgView.getSelectionModel().selectFirst();
        runButton.setOnMouseClicked(event -> {
            try {
                if (controller.oneStepForGUI()) {
                    initializeProgramStates();
                    setAll();
                }
            }
            catch(Exception exp)
            {
                exp.printStackTrace();
            }
        });

        prgView.setOnMouseClicked(event -> {
            try {
                setAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        heapAddr.setCellValueFactory(new PropertyValueFactory<>("Address"));
        heapValue.setCellValueFactory(new PropertyValueFactory<>("Value"));

        symName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        symValue.setCellValueFactory(new PropertyValueFactory<>("Value"));

        //locationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
        //valueId.setCellValueFactory(new PropertyValueFactory<>("Value"));

        setAll();

    }

    private void initializeProgramStates()
    {
        ObservableList<String> programListId = FXCollections.observableArrayList();
        prgListIds = (ArrayList<Integer>) controller.getIds();

        for(int id: prgListIds)
            programListId.add(String.valueOf(id));

        prgView.setItems(programListId);
        numPrgStates.setText("Number of program states: "+ String.valueOf(prgListIds.size()));

        if(size!=programListId.size())
        {
            prgView.getSelectionModel().selectFirst();
            size = programListId.size();
        }
    }

    private void setAll() throws Exception {

        ProgramState state = controller.getProgramStateById(Integer.parseInt(prgView.getSelectionModel().getSelectedItem()));
        symbolTabl = state.getSymbolTable();
        executionStack = state.getExecutionStack();
        heap = state.getHeap();
        out = state.getOut();
        fileTable = state.getFileTable();
        latchTable = state.getLatchTable();


        ObservableList<String> execStackObj = FXCollections.observableArrayList();
        int index = executionStack.getAll().size()-1;
        while(index >= 0)
        {
            execStackObj.add(executionStack.getAll().get(index).toString());
            index--;
        }
        exeStackView.setItems(execStackObj);


        ObservableList<String> outObj = FXCollections.observableArrayList();
        for(Value v: out.getAll())
            outObj.add(v.toString());
        outView.setItems(outObj);


        ObservableList<String> fileObj = FXCollections.observableArrayList();
        for(StringValue sv: fileTable.getAll().keySet())
            fileObj.add(sv.toString());
        fileTblView.setItems(fileObj);


        ObservableList<HeapObject> heapOb = FXCollections.observableArrayList();
        for(int key: heap.getAll().keySet())
        {
            heapOb.add(new HeapObject(key, heap.getValue(key)));

        }
        heapView.setItems(heapOb);

        ObservableList<SymbolTableObject> symbolTableOb = FXCollections.observableArrayList();
        for(String name: symbolTabl.getAll().keySet())
        {
            symbolTableOb.add(new SymbolTableObject(name, symbolTabl.lookFor(name)));
        }
        symTblView.setItems(symbolTableOb);


        /*ObservableList<LatchTableObj> latch = FXCollections.observableArrayList();
        for(int key: latchTable.getAll().keySet())
        {
            latch.add(new LatchTableObj(key, heap.getValue(key)));

        }
        latchView.setItems(latch);
*/

    }

}

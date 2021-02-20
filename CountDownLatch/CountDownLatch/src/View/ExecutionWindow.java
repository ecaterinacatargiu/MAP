package View;

import Controller.Controller;
import Model.Exceptions.MyException;
import Model.Statments.IStmt;
import Model.Structure.*;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.Repository;
import View.Utils.HeapObj;
import View.Utils.SymTblObj;
import com.sun.xml.internal.bind.v2.runtime.property.PropertyFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;

import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class ExecutionWindow {
    IStmt stmt;
    @FXML
    ListView<String> prgView;
    @FXML
    ListView<String> exeStackView;
    @FXML
    ListView<String> outView;
    @FXML
    ListView<String> fileTblView;
    @FXML
    TableView<HeapObj> heapView;
    @FXML
    TableColumn<HeapObj, Integer> heapAddr;
    @FXML
    TableColumn<HeapObj, Value> heapValue;
    @FXML
    TableView<SymTblObj> symTblView;
    @FXML
    TableColumn<SymTblObj, String> symName;
    @FXML
    TableColumn<SymTblObj, Value> symValue;
    @FXML
    Label stmtName;
    @FXML
    Label numPrgStates;
    @FXML
    Button runButton;


    MyIDictionary<String, Value> symTbl;
    MyIHeap heap;
    MyIStack<IStmt> exeStack;
    MyIList<Value> out;
    MyIFileTable<StringValue, BufferedReader> fileTable;
    ArrayList<Integer> prgListIds;
    private Controller ctrl;
    int size;

    public ExecutionWindow(IStmt stmt)
    {
        this.stmt = stmt;
    }

    @FXML
    public void initialize() {
        stmtName.setText(stmt.toString());
        PrgState state = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), new MyFileTable<>(), new MyHeap(), new MyLatch(), stmt);
        IRepository repo = new Repository("repo.txt");
        repo.addState(state);
        ctrl = new Controller(repo);
        initPrgState();
        prgView.getSelectionModel().selectFirst();
        runButton.setOnMouseClicked(event -> {
            try {
                if(ctrl.oneStepGUI()) {
                    initPrgState();
                    setAll();
                }
            }
            catch (MyException exp)
            {
                exp.printStackTrace();
            }
        });
        prgView.setOnMouseClicked(event -> setAll());
        heapAddr.setCellValueFactory(new PropertyValueFactory<>("Address"));
        heapValue.setCellValueFactory(new PropertyValueFactory<>("Value"));

        symName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        symValue.setCellValueFactory(new PropertyValueFactory<>("Value"));


        setAll();
    }

    public void initPrgState()
    {
        ObservableList<String> prgListId = FXCollections.observableArrayList();
        prgListIds = (ArrayList<Integer>) ctrl.getIdList();

        for(int id : prgListIds)
            prgListId.add(String.valueOf(id));

        prgView.setItems(prgListId);
        numPrgStates.setText("No of Prg States: "+ String.valueOf(prgListIds.size()));
        if(size != prgListId.size())
        {
            prgView.getSelectionModel().selectFirst();
            size = prgListId.size();
        }
    }

    public void setAll()
    {
        PrgState state = ctrl.getPrgStateById(Integer.parseInt(prgView.getSelectionModel().getSelectedItem()));
        symTbl = state.getSymTable();
        exeStack = state.getExeStack();
        heap = state.getHeap();
        out = state.getOut();
        fileTable = state.getFileTable();

        ObservableList<String> exeStackObs = FXCollections.observableArrayList();
        int index = exeStack.getAll().size()-1;
        while (index >= 0)
        {
            exeStackObs.add(exeStack.getAll().get(index).toString());
            index--;
        }
        exeStackView.setItems(exeStackObs);

        ObservableList<String> outObs = FXCollections.observableArrayList();
        for(Value v : out.getAll())
            outObs.add(v.toString());
        outView.setItems(outObs);

        ObservableList<String> fileObs = FXCollections.observableArrayList();
        for(StringValue sv : fileTable.getAll().keySet())
            fileObs.add(sv.toString());
        fileTblView.setItems(fileObs);

        ObservableList<HeapObj> heapObs = FXCollections.observableArrayList();
        for(int key : heap.getAll().keySet())
            heapObs.add(new HeapObj(key, heap.getValue(key)));
        heapView.setItems(heapObs);

        ObservableList<SymTblObj> symTblObs = FXCollections.observableArrayList();
        for(String name : symTbl.getAll().keySet())
            symTblObs.add(new SymTblObj(name, symTbl.lookup(name)));
        symTblView.setItems(symTblObs);
    }

}

package View;

import Model.Exceptions.MyException;
import Model.Expressions.*;
import Model.Statments.*;
import Model.Structure.MyDictionary;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CountDownLatch;

public class SelectionWindowCtrl {

    @FXML
    ListView<String> stmtString;

    List<IStmt> stmtList = new ArrayList<>();

    @FXML
    public void initialize(){
        //int v; v=2; print (v)
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));

        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp("+",new ValueExp(new IntValue(2)),new
                                ArithExp("*",new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp("+",new VarExp("a"), new
                                        ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));

        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),
                                        new AssignStmt("v",new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new
                                                VarExp("v"))))));

        IStmt ex4 = new CompStmt(new VarDeclStmt("s",new StringType()),
                new CompStmt(new AssignStmt("s",new ValueExp(new StringValue("ana"))),
                        new PrintStmt(new VarExp("s"))));
        IStmt ex5 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new openRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new readFile(new VarExp("varf"),"varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new closeRFile(new VarExp("varf"))))))))));

        IStmt ex6 = new IfStmt(new RelExp(new ValueExp(new IntValue(40)), new ValueExp(new IntValue(30)),
                "=="),new PrintStmt(new ValueExp(new StringValue("ana"))),
                new PrintStmt(new ValueExp(new StringValue("ion"))));

        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new newH("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new newH("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new VarExp("a")))))));


        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new newH("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new newH("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))),
                                                new PrintStmt(new ArithExp("+",new rH(new rH(new VarExp("a"))),new ValueExp(new IntValue(5)))))))));


        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new newH("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))),
                                new CompStmt(new wH("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp("+", new rH(new VarExp("v")),new ValueExp(new IntValue(5))))))));


        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStmt ex10 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new newH("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new newH("a",new VarExp("v")),
                                        new CompStmt(new newH("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new rH(new rH(new VarExp("a")))))))));
        // int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelExp(new VarExp("v"),new ValueExp(new IntValue(0)),">"),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v",new ArithExp("-", new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));


        //int v; Ref int a; v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(rH(a)));
        // print(v);print(rH(a))

        IStmt inFork=new CompStmt(new wH("a",new ValueExp(new IntValue(30))),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(32))),
                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rH(new VarExp("a"))))));

        IStmt inFork2=new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(32))),
                new PrintStmt(new VarExp("v")));


        IStmt ex12 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(10))),
                                new CompStmt(new newH("a",new ValueExp(new IntValue(22))),
                                        new CompStmt(new forkStmt(inFork),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rH(new VarExp("a")))))))));

        //Wrong type check
        //bool a;a=13;
        IStmt ex13 = new CompStmt(new VarDeclStmt("a", new BoolType()), new AssignStmt("a", new ValueExp(new IntValue(13))));

        //Wrong type check
        //Ref Int a; new(a,'ana')
        IStmt ex14 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new newH("a", new ValueExp(new StringValue("ana"))));

        IStmt ex15 = new CompStmt(new VarDeclStmt("a", new IntType()),new CompStmt(
                new VarDeclStmt("b", new IntType()), new CompStmt(new VarDeclStmt("c", new IntType()),
                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(1))),
                        new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(2))),
                                new CompStmt(new AssignStmt("c", new ValueExp(new IntValue(5))),
                                        new CompStmt(new SwitchStmt(new RelExp(new VarExp("a"), new VarExp("b"), "<"),
                                                new ArithExp("*", new VarExp("b"), new VarExp("c")),
                                                new ValueExp(new IntValue(10)),
                                                new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))),
                                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))), new PrintStmt(new ValueExp(new IntValue(200)))),
                                                new PrintStmt(new ValueExp(new IntValue(300)))), new PrintStmt(new ValueExp(new IntValue(300))))))))));

        IStmt fork1 = new CompStmt(new wH("v1", new ArithExp("*", new rH(new VarExp("v1")), new ValueExp(new IntValue(10)))),
                new CompStmt(new PrintStmt(new rH(new VarExp("v1"))),
                        new countDown("cnt")));

        IStmt fork2 = new CompStmt(new wH("v2", new ArithExp("*", new rH(new VarExp("v2")), new ValueExp(new IntValue(10)))),
                new CompStmt(new PrintStmt(new rH(new VarExp("v2"))),
                        new countDown("cnt")));

        IStmt fork3 = new CompStmt(new wH("v3", new ArithExp("*", new rH(new VarExp("v3")), new ValueExp(new IntValue(10)))),
                new CompStmt(new PrintStmt(new rH(new VarExp("v3"))),
                        new countDown("cnt")));



        IStmt ex16 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())),
                        new CompStmt(new VarDeclStmt("v3", new RefType(new IntType())),
                                new CompStmt(new newH("v1", new ValueExp(new IntValue(2))),
                                        new CompStmt(new newH("v2", new ValueExp(new IntValue(3))),
                                                new CompStmt(new newH("v3", new ValueExp(new IntValue(4))),
                                                        new CompStmt(
                                                                new newLatch("cnt", new rH(new VarExp("v2"))),
                                                                new CompStmt(new forkStmt(fork1),
                                                                                new CompStmt(new forkStmt(fork2),
                                                                                                        new CompStmt(new forkStmt(fork3),
                                                                                                                                new CompStmt(new awaitStmt("cnt"),
                                                                                                                                        new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),new CompStmt(new countDown("cnt"), new PrintStmt(new ValueExp(new IntValue(100))))))))))))))));

        stmtList.add(ex1);
        stmtList.add(ex2);
        stmtList.add(ex3);
        stmtList.add(ex4);
        stmtList.add(ex5);
        stmtList.add(ex6);
        stmtList.add(ex7);
        stmtList.add(ex8);
        stmtList.add(ex9);
        stmtList.add(ex10);
        stmtList.add(ex11);
        stmtList.add(ex12);
        stmtList.add(ex13);
        stmtList.add(ex14);
        stmtList.add(ex15);
        stmtList.add(ex16);


        ObservableList<String> observableList = FXCollections.observableArrayList();
        
        for(IStmt st : stmtList)
        {
            observableList.add(st.toString());
        }
        stmtString.setItems(observableList);
        stmtString.getSelectionModel().selectFirst();

    }

    @FXML
    public void executeButton(Event evt) throws IOException {
        IStmt stmt = stmtList.get(stmtString.getSelectionModel().getSelectedIndex());

        try {
            stmt.typeCheck(new MyDictionary<>());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ExecutionWindow.fxml"));
            loader.setController(new ExecutionWindow(stmt));
            Stage stage = new Stage();
            Parent root = loader.load();
            stage.setTitle("Running program");
            stage.setScene(new Scene(root, 800, 630));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        catch (MyException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Typecheck failed");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(e.toString())));
            alert.showAndWait();

        }


        //System.out.println(stmt.toString());
    }


}

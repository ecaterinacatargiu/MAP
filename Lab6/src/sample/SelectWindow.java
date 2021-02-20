package sample;

import ADT.Dictionary;
import Model.Expression.*;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.ReferenceType;
import Model.Type.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
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


public class SelectWindow {

    @FXML
    ListView<String> stmtString;
    private List<IStatement> statementList = new ArrayList<>();


    @FXML
    public void initialize()
    {
        //int v; v=2; print (v)
        IStatement ex1 = new CompoundStatement(new VarDecStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        IStatement ex2 = new CompoundStatement( new VarDecStatement("a",new IntType()),
                new CompoundStatement(new VarDecStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression("*",new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression("+",new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));

        IStatement ex3 = new CompoundStatement(new VarDecStatement("a",new BoolType()),
                new CompoundStatement(new VarDecStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new
                                                VariableExpression("v"))))));

        IStatement ex4 = new CompoundStatement(new VarDecStatement("s",new StringType()),
                new CompoundStatement(new AssignmentStatement("s",new ValueExpression(new StringValue("ana"))),
                        new PrintStatement(new VariableExpression("s"))));

        IStatement ex5 = new CompoundStatement(new VarDecStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new openRFile(new VariableExpression("varf")),
                                new CompoundStatement(new VarDecStatement("varc", new IntType()),
                                        new CompoundStatement(new readFile(new VariableExpression("varf"),"varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new readFile(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new closeRFile(new VariableExpression("varf"))))))))));

        IStatement ex6 = new IfStatement(new RelationalExpression(new ValueExpression(new IntValue(40)), new ValueExpression(new IntValue(30)),
                "=="),new PrintStatement(new ValueExpression(new StringValue("ana"))),
                new PrintStatement(new ValueExpression(new StringValue("ion"))));


        IStatement ex7 = new CompoundStatement(new VarDecStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VarDecStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new VariableExpression("a")))))));

        IStatement ex8 = new CompoundStatement(new VarDecStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VarDecStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression("+",new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))),new ValueExpression(new IntValue(5)))))))));

        IStatement ex9 = new CompoundStatement(new VarDecStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression("+", new ReadHeapExpression(new VariableExpression("v")),new ValueExpression(new IntValue(5))))))));

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStatement ex10 = new CompoundStatement(new VarDecStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VarDecStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a",new VariableExpression("v")),
                                        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))))))));

        // int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement ex11 = new CompoundStatement(new VarDecStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(0)),">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v",new ArithmeticExpression("-", new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));

        //int v; Ref int a; v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(rH(a)));
        // print(v);print(rH(a))

        IStatement inFork=new CompoundStatement(new WriteHeapStatement("a",new ValueExpression(new IntValue(30))),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))));
        IStatement inFork2=new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
                new PrintStatement(new VariableExpression("v")));
        IStatement ex12 = new CompoundStatement(new VarDecStatement("v",new IntType()),
                new CompoundStatement(new VarDecStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new HeapAllocationStatement("a",new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(inFork),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));


        //Wrong type check
        //bool a;a=13;
        IStatement ex13 = new CompoundStatement(new VarDecStatement("a", new BoolType()), new AssignmentStatement("a", new ValueExpression(new IntValue(13))));

        //Wrong type check
        //Ref Int a; new(a,'ana')
        IStatement ex14 = new CompoundStatement(new VarDecStatement("a", new ReferenceType(new IntType())), new HeapAllocationStatement("a", new ValueExpression(new StringValue("ana"))));


        IStatement ex15 = new CompoundStatement(new VarDecStatement("v",new IntType()),new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(20))),
                new CompoundStatement(new WaitStatement(new ValueExpression(new IntValue(10))),new PrintStatement(new ArithmeticExpression("*",new VariableExpression("v"),new ValueExpression(new IntValue(10)))))));

        IStatement ex16 = new CompoundStatement(new VarDecStatement("v",new IntType()),new CompoundStatement(new AssignmentStatement("v",new ValueExpression( new IntValue(20))),
                new CompoundStatement(new ForStatement(new ValueExpression(new IntValue(0)), new ValueExpression(new IntValue(3)), new ArithmeticExpression("+", new VariableExpression("v"),new ValueExpression(new IntValue(1))),
                        new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new ValueExpression(new IntValue(1)))))),
                        new PrintStatement(new ArithmeticExpression("*", new ValueExpression(new IntValue(10)), new VariableExpression("v"))))));


        IStatement ex17 = new CompoundStatement(new VarDecStatement("v1",new IntType()),new CompoundStatement(new VarDecStatement("v2",new IntType()),
                new CompoundStatement(new AssignmentStatement("v1",new ValueExpression(new IntValue(2))),new CompoundStatement(new AssignmentStatement("v2",new ValueExpression(new IntValue(3))),
                        new IfStatement(new RelationalExpression(new VariableExpression("v1"),new ValueExpression(new IntValue(0)),"!="),
                                new PrintStatement(new MULExpression(new VariableExpression("v1"),new VariableExpression("v2"))),new PrintStatement(new VariableExpression("v1")))))));


        /*Ref int a; Ref int b; int v;
        new(a,0); new(b,0);
        wh(a,1); wh(b,2);
        v=(rh(a)<rh(b))?100:200;
        print(v);
        v= ((rh(b)-2)>rh(a))?100:200;
        print(v);*/


        IStatement exCond = new CompoundStatement(new VarDecStatement("a", new ReferenceType(new IntType())),
                new CompoundStatement(new VarDecStatement("b", new ReferenceType(new IntType())),
                        new CompoundStatement(new VarDecStatement("v",new IntType()),
                                new CompoundStatement(new HeapAllocationStatement("a",new ValueExpression(new IntValue(0))),
                                        new CompoundStatement(new HeapAllocationStatement("b",new ValueExpression(new IntValue(0))),
                                                new CompoundStatement(new WriteHeapStatement("a",new ValueExpression(new IntValue(1))),
                                                        new CompoundStatement(new WriteHeapStatement("b",new ValueExpression(new IntValue(2))),
                                                                new CompoundStatement(new ConditionalAssignemntStatement(new RelationalExpression(new ReadHeapExpression(new VariableExpression("a")), new ReadHeapExpression(new VariableExpression("b")),"<"),new ValueExpression(new IntValue(100)),new ValueExpression(new IntValue(200)),"v"),
                                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                                new CompoundStatement(new ConditionalAssignemntStatement(new RelationalExpression(new ArithmeticExpression("-",new ReadHeapExpression(new VariableExpression("b")), new ValueExpression(new IntValue(2))),new ReadHeapExpression(new VariableExpression("a")),">"),new ValueExpression(new IntValue(100)),new ValueExpression(new IntValue(200)),"v"), new PrintStatement(new VariableExpression("v"))))))))))));






        IStatement fork1 = new CompoundStatement(new WriteHeapStatement("v1", new ArithmeticExpression("*", new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)))),
                new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))),
                        new CountDownStatement("cnt")));

        IStatement fork2 = new CompoundStatement(new WriteHeapStatement("v2", new ArithmeticExpression("*", new ReadHeapExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(10)))),
                new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v2"))),
                        new CountDownStatement("cnt")));

        IStatement fork3 = new CompoundStatement(new WriteHeapStatement("v3", new ArithmeticExpression("*", new ReadHeapExpression(new VariableExpression("v3")), new ValueExpression(new IntValue(10)))),
                new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v3"))),
                        new CountDownStatement("cnt")));



        /*
        new(v1,2);new(v2,3);new(v3,4);newLatch(cnt,rH(v2));
        fork(wh(v1,rh(v1)*10));print(rh(v1));countDown(cnt));
        fork(wh(v2,rh(v2)*10));print(rh(v2));countDown(cnt));
        fork(wh(v3,rh(v3)*10));print(rh(v3));countDown(cnt));
        await(cnt);
        print(cnt);
        countDown(cnt);
        print(cnt)
        The final Out should be {20,id-first-child,30,id-second-child,40, id-thirdchild,0,0}
        where id-first-child, id-second-child and id-third-child are the
        unique identifiers of those three new threads created by fork
         */

        IStatement ex = new CompoundStatement(new VarDecStatement("v1", new ReferenceType(new IntType())),
                new CompoundStatement(new VarDecStatement("v2", new ReferenceType(new IntType())),
                        new CompoundStatement(new VarDecStatement("v3", new ReferenceType(new IntType())),
                                new CompoundStatement(new HeapAllocationStatement("v1", new ValueExpression(new IntValue(2))),
                                        new CompoundStatement(new HeapAllocationStatement("v2", new ValueExpression(new IntValue(3))),
                                                new CompoundStatement(new HeapAllocationStatement("v3", new ValueExpression(new IntValue(4))),
                                                        new CompoundStatement(
                                                                new NewLatchStatement("cnt", new ReadHeapExpression(new VariableExpression("v2"))),
                                                                new CompoundStatement(new ForkStatement(fork1),
                                                                        new CompoundStatement(new ForkStatement(fork2),
                                                                                new CompoundStatement(new ForkStatement(fork3),
                                                                                        new CompoundStatement(new AwaitStatement("cnt"),
                                                                                                new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(100))),new CompoundStatement(new CountDownStatement("cnt"), new PrintStatement(new ValueExpression(new IntValue(100))))))))))))))));


        statementList.add(ex1);
        statementList.add(ex2);
        statementList.add(ex3);
        statementList.add(ex4);
        statementList.add(ex5);
        statementList.add(ex6);
        statementList.add(ex7);
        statementList.add(ex8);
        statementList.add(ex9);
        statementList.add(ex10);
        statementList.add(ex11);
        statementList.add(ex12);
        statementList.add(ex13);
        statementList.add(ex14);
        statementList.add(ex15);
        //statementList.add(ex16);
       // statementList.add(ex17);
        //statementList.add(fork1);
        //statementList.add(fork2);
        //statementList.add(fork3);
        statementList.add(ex);
        statementList.add(exCond);




        ObservableList<String> observableList = FXCollections.observableArrayList();

        for(IStatement st: statementList)
        {
            observableList.add(st.toString());
        }

        stmtString.setItems(observableList);
        stmtString.getSelectionModel().selectFirst();
    }


    @FXML
    public void executeButton(Event event) throws Exception
    {
        IStatement statement = statementList.get(stmtString.getSelectionModel().getSelectedIndex());

        try{
            statement.typeCheck(new Dictionary<>());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ExecutionWindow.fxml"));
            loader.setController(new ExecuteWindow(statement));

            Stage stage = new Stage();
            Parent root = loader.load();

            stage.setTitle("Running program");
            stage.setScene(new Scene(root, 650, 630));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Typecheck failed");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(e.toString())));
            alert.showAndWait();
            //e.printStackTrace();
        }

        //System.out.println(statement.toString());
    }

}

package View;

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

public class Interpreter {

    public static void main(String[] args) throws Exception {

        //////////////////////////////EX1////////////////////////
        //int v; v=2; print (v)
        IStatement ex1 = new CompoundStatement(new VarDecStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        ProgramState state1 = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(),new MyFileTable(), new MyHeap(), ex1);
        IRepository repo1 = new Repository("D:\\An II\\MAP\\Lab4\\repo.txt");
        repo1.addProgram(state1);
        Controller ctrl1 = new Controller(repo1);


        //////////////////////////////EX2////////////////////////
        IStatement ex2 = new CompoundStatement( new VarDecStatement("a",new IntType()),
                new CompoundStatement(new VarDecStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression("*",new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression("+",new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        ProgramState state2 = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), new MyFileTable(), new MyHeap(), ex2);
        IRepository repo2 = new Repository("D:\\An II\\MAP\\Lab4\\repo.txt");
        repo2.addProgram(state2);
        Controller ctrl2 = new Controller(repo2);


        //////////////////////////////EX3////////////////////////
        IStatement ex3 = new CompoundStatement(new VarDecStatement("a",new BoolType()),
                new CompoundStatement(new VarDecStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new
                                                VariableExpression("v"))))));
        ProgramState state3 = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), new MyFileTable(), new MyHeap(), ex3);
        IRepository repo3 = new Repository("D:\\An II\\MAP\\Lab4\\repo.txt");
        repo3.addProgram(state3);
        Controller ctrl3 = new Controller(repo3);


        //////////////////////////////EX4////////////////////////
        IStatement ex4 = new CompoundStatement(new VarDecStatement("s",new StringType()),
                new CompoundStatement(new AssignmentStatement("s",new ValueExpression(new StringValue("ana"))),
                        new PrintStatement(new VariableExpression("s"))));
        ProgramState state4 =new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), new MyFileTable(), new MyHeap(), ex4);
        IRepository repo4 = new Repository("D:\\An II\\MAP\\Lab4\\repo.txt");
        repo4.addProgram(state4);
        Controller ctrl4 = new Controller(repo4);


        //////////////////////////////EX5////////////////////////
        IStatement ex5 = new CompoundStatement(new VarDecStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new openRFile(new VariableExpression("varf")),
                                new CompoundStatement(new VarDecStatement("varc", new IntType()),
                                        new CompoundStatement(new readFile(new VariableExpression("varf"),"varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new readFile(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new closeRFile(new VariableExpression("varf"))))))))));
        ProgramState state5 = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), new MyFileTable(), new MyHeap(), ex5);
        IRepository repo5 = new Repository("D:\\An II\\MAP\\Lab4\\repo.txt");
        repo5.addProgram(state5);
        Controller ctrl5 = new Controller(repo5);


        //////////////////////////////EX6////////////////////////
        IStatement ex6 = new IfStatement(new RelationalExpression(new ValueExpression(new IntValue(40)), new ValueExpression(new IntValue(30)),
                "=="),new PrintStatement(new ValueExpression(new StringValue("ana"))),
                new PrintStatement(new ValueExpression(new StringValue("ion"))));
        ProgramState state6 = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), new MyFileTable(), new MyHeap(), ex6);
        IRepository repo6 = new Repository("D:\\An II\\MAP\\Lab4\\repo.txt");
        repo6.addProgram(state6);
        Controller ctrl6 = new Controller(repo6);


        //////////////////////////////EX7////////////////////////
        IStatement ex7 = new CompoundStatement(new VarDecStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VarDecStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new VariableExpression("a")))))));

        ProgramState state7 =new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), new MyFileTable(), new MyHeap(), ex7);
        IRepository repo7 = new Repository("D:\\An II\\MAP\\Lab4\\repo.txt");
        repo7.addProgram(state7);
        Controller ctrl7 = new Controller(repo7);


        //////////////////////////////EX8////////////////////////
        IStatement ex8 = new CompoundStatement(new VarDecStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VarDecStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression("+",new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))),new ValueExpression(new IntValue(5)))))))));
        ProgramState state8 = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), new MyFileTable(), new MyHeap(), ex8);
        IRepository repo8 = new Repository("D:\\An II\\MAP\\Lab4\\repo.txt");
        repo8.addProgram(state8);
        Controller ctrl8 = new Controller(repo8);


        //////////////////////////////EX9////////////////////////
        IStatement ex9 = new CompoundStatement(new VarDecStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression("+", new ReadHeapExpression(new VariableExpression("v")),new ValueExpression(new IntValue(5))))))));
        ProgramState state9 = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), new MyFileTable(), new MyHeap(), ex9);
        IRepository repo9 = new Repository("D:\\An II\\MAP\\Lab4\\repo.txt");
        repo9.addProgram(state9);
        Controller ctrl9 = new Controller(repo9);


        //////////////////////////////EX10////////////////////////
        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStatement ex10 = new CompoundStatement(new VarDecStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VarDecStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a",new VariableExpression("v")),
                                        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))))))));
        ProgramState state10 = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), new MyFileTable(), new MyHeap(), ex10);
        IRepository repo10 = new Repository("D:\\An II\\MAP\\Lab4\\repo.txt");
        repo10.addProgram(state10);
        Controller ctrl10 = new Controller(repo10);


        //////////////////////////////EX11////////////////////////
        // int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement ex11 = new CompoundStatement(new VarDecStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(0)),">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v",new ArithmeticExpression("-", new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));

        ProgramState state11 = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), new MyFileTable<>(),new MyHeap(), ex11);
        IRepository repo11 = new Repository("repo.txt");
        repo11.addProgram(state11);
        Controller ctrl11 = new Controller(repo11);


        //////////////////////////////EX12/////////////////////////
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

        ProgramState state12 = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), new MyFileTable<>(),new MyHeap(), ex12);
        IRepository repo12 = new Repository("repo.txt");
        repo12.addProgram(state12);
        Controller ctrl12 = new Controller(repo12);




        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunCommand("1",ex1.toString(),ctrl1));
        //menu.addCommand(new RunCommand("2",ex2.toString(),ctrl2));
        //menu.addCommand(new RunCommand("3",ex3.toString(),ctrl3));
        //menu.addCommand(new RunCommand("4",ex4.toString(),ctrl4));
        //menu.addCommand(new RunCommand("5",ex5.toString(),ctrl5));
        //menu.addCommand(new RunCommand("6",ex6.toString(),ctrl6));
        //menu.addCommand(new RunCommand("7",ex7.toString(),ctrl7));
        //menu.addCommand(new RunCommand("8",ex8.toString(),ctrl8));
        //menu.addCommand(new RunCommand("9",ex9.toString(),ctrl9));
        //menu.addCommand(new RunCommand("10",ex10.toString(),ctrl10));
        //menu.addCommand(new RunCommand("11",ex11.toString(),ctrl11));
        //menu.addCommand(new RunCommand("12",ex12.toString(),ctrl12));

        menu.show();
    }
}

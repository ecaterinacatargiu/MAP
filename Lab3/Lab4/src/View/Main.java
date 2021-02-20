package View;

import ADT.*;
import Controller.Controller;
import Controller.IController;
import Model.Expression.ArithmeticExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.Expression.LogicalExpression;
import Model.State.ProgramState;
import Model.Statement.*;
import Model.Statement.PrintStatement;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Values.IntValue;
import Model.Values.BoolValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.Repository;

import javax.swing.plaf.basic.BasicIconFactory;

public class Main
{
    public static void runExample1()
    {
        IStatement ex1 = new CompoundStatement(new VarDecStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        ProgramState state1 = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), ex1);
        IRepository repo1 = new Repository();
        repo1.addProgram(state1);
        Controller ctrl1 = new Controller(repo1);
        try {
            System.out.println("Example 1");
            ctrl1.allSteps();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runExample2()
    {
        //int a;int b; a=2+3*5;b=a+1;Print(b)
        IStatement ex2 = new CompoundStatement( new VarDecStatement("a",new IntType()),
                new CompoundStatement(new VarDecStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression("*",new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression("+",new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        ProgramState state2 = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), ex2);
        IRepository repo2 = new Repository();
        repo2.addProgram(state2);
        Controller ctrl2 = new Controller(repo2);
        try {
            System.out.println("Example 2");
            ctrl2.allSteps();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void runExample3()
    {
        //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStatement ex3 = new CompoundStatement(new VarDecStatement("a",new BoolType()),
                new CompoundStatement(new VarDecStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new
                                                VariableExpression("v"))))));
        ProgramState state3 = new ProgramState(new MyStack<IStatement>(), new Dictionary<String, Value>(), new MyList<Value>(), ex3);
        IRepository repo3 = new Repository();
        repo3.addProgram(state3);
        Controller ctrl3 = new Controller(repo3);
        try {
            System.out.println("Example 3");
            ctrl3.allSteps();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args)
    {
        runExample1();
        //runExample2();
        //runExample3();


    }
}

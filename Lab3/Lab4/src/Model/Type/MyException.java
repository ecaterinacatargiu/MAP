package Model.Type;

public class MyException extends Exception {

    public MyException(String id, Type expectedType, Type actualType)
    {
        super("Can not assign to variale"+id+"of type"+expectedType.toString()+"the evaluation of type"+actualType);
    }

    public MyException(Type expectedType, Type actualType)
    {
        super("Expected type: "+expectedType+"but got"+actualType);
    }
}

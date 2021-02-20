package ADT;

import java.util.Stack;

public interface IStack<T>
{
    void push(T elem);
    T pop();
    boolean isEmpty();
    int size();
    String toString();

    Stack<T> getAll();
}

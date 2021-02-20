package ADT;

import java.util.Stack;

public class MyStack<T> implements IStack<T>
{
    private Stack<T> stack;

    public MyStack()
    {
        this.stack=new Stack<T>();
    }

    @Override
    public void push(T elem)
    {
        stack.push(elem);
    }

    @Override
    public T pop()
    {
        return stack.pop();
    }

    @Override
    public int size()
    {
        return stack.size();
    }

   @Override
    public boolean isEmpty()
   {
       return stack.isEmpty();
   }

   public Stack<T> getStack()
   {
       return this.stack;
   }

   @Override
    public String toString()
   {
       String stk = "";
       int index = stack.size()-1;
       for(;index>=0;--index)
       {
           stk += stack.get(index).toString();
       }
       return stk;
   }

    @Override
    public Stack<T> getAll() {
        return stack;
    }
}

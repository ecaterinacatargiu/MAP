package Model.Structure;

import Model.Values.Value;

import java.util.ArrayList;

public interface MyIList<T> {
    public void addToList(T e);
    ArrayList<T> getAll();
}

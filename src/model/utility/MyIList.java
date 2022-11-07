package model.utility;

import model.exceptions.InterpreterException;

import java.util.ArrayList;

public interface MyIList<T> extends IDeepCopyable {
    void add(T item);
    ArrayList<T> toArrayList();
    MyIList<T> deepCopy() throws InterpreterException;
}

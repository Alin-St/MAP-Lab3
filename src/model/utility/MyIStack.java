package model.utility;

import model.exceptions.AdtException;
import model.exceptions.InterpreterException;

import java.util.ArrayList;

public interface MyIStack<T> extends IDeepCopyable {
    void push(T item);
    T pop();
    boolean empty();
    ArrayList<T> toArrayList() throws AdtException;
    MyIStack<T> deepCopy() throws InterpreterException;
}

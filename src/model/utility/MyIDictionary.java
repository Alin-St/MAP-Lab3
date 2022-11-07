package model.utility;

import model.exceptions.InterpreterException;

import java.util.ArrayList;
import java.util.Map;

public interface MyIDictionary<TKey, TValue> extends IDeepCopyable {
    void put(TKey key, TValue value);
    TValue get(TKey key);
    boolean containsKey(TKey key);
    ArrayList<Map.Entry<TKey, TValue>> toArrayList();
    MyIDictionary<TKey, TValue> deepCopy() throws InterpreterException;
}

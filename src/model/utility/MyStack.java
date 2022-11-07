package model.utility;

import model.exceptions.AdtException;
import model.exceptions.InterpreterException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {

    private final Stack<T> _items = new Stack<>();
    private final Class<T> _type;

    public MyStack(Class<T> type) {
        _type = type;
    }

    public MyStack(Class<T> type, T firstItem) {
        this(type);
        _items.push(firstItem);
    }

    @Override
    public void push(T item) {
        _items.push(item);
    }

    @Override
    public T pop() {
        return _items.pop();
    }

    @Override
    public boolean empty() {
        return _items.empty();
    }

    @Override
    public ArrayList<T> toArrayList() throws AdtException {
        var shallowCopy = (Stack<?>)_items.clone();
        var result = new ArrayList<T>();
        while (!shallowCopy.empty()) {
            var item = shallowCopy.pop();
            if (!_type.isInstance(item))
                throw new AdtException("Invalid item type.");
            result.add(_type.cast(item));
        }
        return result;
    }

    @Override
    public MyStack<T> deepCopy() throws InterpreterException {
        var itemsArray = toArrayList();
        Collections.reverse(itemsArray);
        var result = new MyStack<>(_type);
        for (var item : itemsArray) {
            if (!(item instanceof IDeepCopyable dci))
                throw new AdtException("All items must be deep copyable.");
            Object ic = dci.deepCopy();
            if (!_type.isInstance(ic))
                throw new AdtException("Deep copy must return the same object type.");
            result._items.push(_type.cast(ic));
        }
        return result;
    }
}

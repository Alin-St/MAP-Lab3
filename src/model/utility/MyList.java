package model.utility;

import model.exceptions.AdtException;
import model.exceptions.InterpreterException;

import java.util.ArrayList;

public class MyList<T> implements MyIList<T> {

    private final ArrayList<T> _items = new ArrayList<>();
    private final Class<T> _type;

    public MyList(Class<T> type) {
        _type = type;
    }

    @Override
    public void add(T item) {
        _items.add(item);
    }

    @Override
    public ArrayList<T> toArrayList() {
        return new ArrayList<>(_items);
    }

    @Override
    public MyList<T> deepCopy() throws InterpreterException {
        var result = new MyList<>(_type);
        for (var item : _items) {
            if (!(item instanceof IDeepCopyable dci))
                throw new AdtException("All items must be deep copyable.");
            Object ic = dci.deepCopy();
            if (!_type.isInstance(ic))
                throw new AdtException("Deep copy must return the same object type.");
            result._items.add(_type.cast(ic));
        }
        return result;
    }
}

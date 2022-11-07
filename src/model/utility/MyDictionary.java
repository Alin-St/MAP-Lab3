package model.utility;

import model.exceptions.AdtException;
import model.exceptions.InterpreterException;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<TKey, TValue> implements MyIDictionary<TKey, TValue> {

    private final HashMap<TKey, TValue> _items = new HashMap<>();
    private final Class<TKey> _keyType;
    private final Class<TValue> _valueType;

    public MyDictionary(Class<TKey> keyType, Class<TValue> valueType) {
        _keyType = keyType;
        _valueType = valueType;
    }

    @Override
    public void put(TKey key, TValue value) {
        _items.put(key, value);
    }

    @Override
    public TValue get(TKey key) {
        return _items.get(key);
    }

    @Override
    public boolean containsKey(TKey key) {
        return _items.containsKey(key);
    }

    @Override
    public ArrayList<Map.Entry<TKey, TValue>> toArrayList() {
        var result = new ArrayList<Map.Entry<TKey, TValue>>();
        for (var entry : _items.entrySet())
            result.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()));
        return result;
    }

    @Override
    public MyDictionary<TKey, TValue> deepCopy() throws InterpreterException {
        var result = new MyDictionary<>(_keyType, _valueType);
        for (var entry : _items.entrySet()) {
            if (!(entry.getKey() instanceof IDeepCopyable dck) || !(entry.getValue() instanceof  IDeepCopyable dcv))
                throw new AdtException("Keys and values must be deep copyable.");
            Object kc = dck.deepCopy(), vc = dcv.deepCopy();
            if (!_keyType.isInstance(kc) || !_valueType.isInstance(vc))
                throw new AdtException("Deep copy must return the same object type.");
            result._items.put(_keyType.cast(kc), _valueType.cast(vc));
        }
        return result;
    }
}

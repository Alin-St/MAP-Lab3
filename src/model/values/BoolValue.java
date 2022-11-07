package model.values;

import model.types.BoolType;

public class BoolValue implements IValue {

    private final boolean _value;

    public BoolValue(boolean value) {
        _value = value;
    }

    @Override
    public String toString() {
        return _value ? "true" : "false";
    }

    public boolean getValue() { return _value; }

    @Override
    public BoolType getType() { return BoolType.get(); }

    @Override
    public BoolValue deepCopy() {
        return new BoolValue(_value);
    }
}

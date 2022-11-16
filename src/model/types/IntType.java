package model.types;

import model.values.IntValue;

public class IntType implements IType {

    private IntType() {}

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntType;
    }

    @Override
    public IntValue defaultValue() {
        return new IntValue(0);
    }

    @Override
    public IntType deepCopy() {
        return IntType.get();
    }

    private final static IntType _global = new IntType();

    public static IntType get() { return _global; }
}

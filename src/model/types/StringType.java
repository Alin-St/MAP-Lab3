package model.types;

import model.values.IValue;
import model.values.StringValue;

public class StringType implements IType {

    private StringType() {}

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }

    @Override
    public IType deepCopy() {
        return StringType.get();
    }

    private final static StringType _global = new StringType();

    public static StringType get() { return _global; }
}

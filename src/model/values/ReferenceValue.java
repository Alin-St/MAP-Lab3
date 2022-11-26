package model.values;

import model.types.IType;
import model.types.ReferenceType;

public class ReferenceValue implements IValue {

    private final int _address;
    private final IType _valueType;

    public ReferenceValue(int address, IType valueType) {
        _address = address;
        _valueType = valueType;
    }

    public int getAddress() { return _address; }

    @Override
    public String toString() {
        return "(" + _valueType + "*)" + _address;
    }

    @Override
    public ReferenceType getType() {
        return new ReferenceType(_valueType);
    }

    @Override
    public IValue deepCopy() {
        return new ReferenceValue(_address, _valueType.deepCopy());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ReferenceValue other && _address == other._address && _valueType.equals(other._valueType);
    }
}

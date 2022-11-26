package model.expressions;

import model.programState.IHeapTable;
import model.programState.ISymbolTable;
import model.values.IValue;

public class ValueExpression implements IExpression {

    private final IValue _value;

    public ValueExpression(IValue value) {
        _value = value;
    }

    @Override
    public String toString() {
        return _value.toString();
    }

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) {
        return _value;
    }

    @Override
    public ValueExpression deepCopy() {
        return new ValueExpression(_value.deepCopy());
    }
}

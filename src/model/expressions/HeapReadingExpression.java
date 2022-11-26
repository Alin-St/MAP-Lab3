package model.expressions;

import model.exceptions.ExpressionEvaluationException;
import model.programState.IHeapTable;
import model.programState.ISymbolTable;
import model.values.IValue;
import model.values.ReferenceValue;

public class HeapReadingExpression implements IExpression {

    private final IExpression _address;

    public HeapReadingExpression(IExpression address) {
        _address = address;
    }

    @Override
    public String toString() {
        return "readHeap(" + _address + ")";
    }

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) throws ExpressionEvaluationException {
        var addressVal = _address.evaluate(symbolTable, heapTable);
        if (!(addressVal instanceof ReferenceValue refVal))
            throw new ExpressionEvaluationException("Heap reading expression must receive a reference value.");

        int address = refVal.getAddress();
        if (!heapTable.containsKey(address))
            throw new ExpressionEvaluationException("Address not found.");

        return heapTable.get(address);
    }

    @Override
    public HeapReadingExpression deepCopy() {
        return new HeapReadingExpression(_address.deepCopy());
    }
}

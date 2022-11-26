package model.expressions;

import model.exceptions.ExpressionEvaluationException;
import model.programState.IHeapTable;
import model.programState.ISymbolTable;
import model.utility.IDeepCopyable;
import model.values.IValue;

public interface IExpression extends IDeepCopyable {
    String toString();
    IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) throws ExpressionEvaluationException;
    IExpression deepCopy();
}

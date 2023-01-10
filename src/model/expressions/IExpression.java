package model.expressions;

import model.exceptions.ExpressionEvaluationException;
import model.exceptions.InterpreterException;
import model.programState.IHeapTable;
import model.programState.ISymbolTable;
import model.types.IType;
import model.utility.IDeepCopyable;
import model.utility.MyIDictionary;
import model.values.IValue;

public interface IExpression extends IDeepCopyable {
    String toString();
    IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) throws ExpressionEvaluationException;
    IExpression deepCopy();
    IType typeCheck(MyIDictionary<String, IType> typeEnv) throws InterpreterException;
}

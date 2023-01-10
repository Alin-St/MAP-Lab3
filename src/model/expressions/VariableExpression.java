package model.expressions;

import model.exceptions.ExpressionEvaluationException;
import model.exceptions.InterpreterException;
import model.programState.IHeapTable;
import model.programState.ISymbolTable;
import model.types.IType;
import model.utility.MyIDictionary;
import model.values.IValue;

public class VariableExpression implements IExpression {

    private final String _identifier;

    public VariableExpression(String identifier) {
        _identifier = identifier;
    }

    @Override
    public String toString() {
        return _identifier;
    }

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) throws ExpressionEvaluationException {
        if (!symbolTable.containsKey(_identifier))
            throw new ExpressionEvaluationException("Variable '" + _identifier + "' not declared.");
        return symbolTable.get(_identifier);
    }

    @Override
    public VariableExpression deepCopy() {
        return this; // Class is immutable.
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws InterpreterException {
        return typeEnv.get(_identifier);
    }
}

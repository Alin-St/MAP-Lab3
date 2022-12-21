package model.statements;

import model.exceptions.InterpreterException;
import model.exceptions.StatementExecutionException;
import model.expressions.IExpression;
import model.programState.ProgramState;
import model.types.ReferenceType;
import model.values.ReferenceValue;

public class HeapAllocationStatement implements IStatement {

    private final String _identifier;
    private final IExpression _expression;

    public HeapAllocationStatement(String identifier, IExpression expression) {
        _identifier = identifier;
        _expression = expression;
    }

    @Override
    public String toString() {
        return _identifier + " = new(" + _expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        var symbolTable = state.getSymbolTable();
        var heapTable = state.getHeapTable();

        if (!symbolTable.containsKey(_identifier))
            throw new StatementExecutionException("Variable not declared.");

        var identifierType = symbolTable.get(_identifier).getType();

        if (!(identifierType instanceof ReferenceType identifierRType))
            throw new StatementExecutionException("Variable is not a reference type.");

        var expressionValue = _expression.evaluate(symbolTable, heapTable);

        if (!expressionValue.getType().equals(identifierRType.getInnerType()))
            throw new StatementExecutionException("Expression type is not compatible with reference inner type.");

        int heapAddress = heapTable.getFreeAddress();
        heapTable.put(heapAddress, expressionValue);
        symbolTable.put(_identifier, new ReferenceValue(heapAddress, identifierRType.getInnerType()));

        return null;
    }

    @Override
    public HeapAllocationStatement deepCopy() {
        return new HeapAllocationStatement(_identifier, _expression.deepCopy());
    }
}

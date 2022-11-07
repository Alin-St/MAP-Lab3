package model.statements;

import model.ProgramState;
import model.exceptions.InterpreterException;
import model.exceptions.StatementExecutionException;
import model.expressions.IExpression;

public class AssignmentStatement implements IStatement {

    private final String _identifier;
    private final IExpression _expression;

    public AssignmentStatement(String identifier, IExpression expression) {
        _identifier = identifier;
        _expression = expression;
    }

    @Override
    public String toString() {
        return _identifier + " = " + _expression.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        var symbolTable = state.getSymbolTable();

        if (!symbolTable.containsKey(_identifier))
            throw new StatementExecutionException("Variable Id '" + _identifier + "' is not declared.");

        var value = _expression.evaluate(symbolTable);
        var type = symbolTable.get(_identifier).getType();

        if (!value.getType().equals(type))
            throw new StatementExecutionException("Type of expression '" + value.getType().toString() +
                    "' and type of variable '" + type.toString() + "' do not match.");

        symbolTable.put(_identifier, value);
        return state;
    }

    @Override
    public AssignmentStatement deepCopy() {
        return new AssignmentStatement(_identifier, _expression.deepCopy());
    }
}

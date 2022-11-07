package model.statements;

import model.ProgramState;
import model.exceptions.ExpressionEvaluationException;
import model.expressions.IExpression;

public class PrintStatement implements IStatement {

    private final IExpression _expression;

    public PrintStatement(IExpression expression) {
        _expression = expression;
    }

    public String toString() {
        return "print(" + _expression.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException {
        var output = state.getOutputStructure();
        var symbolTable = state.getSymbolTable();
        output.add(_expression.evaluate(symbolTable));
        return state;
    }

    @Override
    public PrintStatement deepCopy() {
        return new PrintStatement(_expression.deepCopy());
    }
}

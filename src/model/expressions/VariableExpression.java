package model.expressions;

import model.exceptions.ExpressionEvaluationException;
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
    public IValue evaluate(MyIDictionary<String, IValue> symbolTable) throws ExpressionEvaluationException {
        if (!symbolTable.containsKey(_identifier))
            throw new ExpressionEvaluationException("Variable '" + _identifier + "' not declared.");
        return symbolTable.get(_identifier);
    }

    @Override
    public VariableExpression deepCopy() {
        return new VariableExpression(_identifier);
    }
}

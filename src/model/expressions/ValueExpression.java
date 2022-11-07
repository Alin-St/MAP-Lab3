package model.expressions;

import model.utility.MyIDictionary;
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
    public IValue evaluate(MyIDictionary<String, IValue> symbolTable) {
        return _value;
    }

    @Override
    public ValueExpression deepCopy() {
        return new ValueExpression(_value.deepCopy());
    }
}

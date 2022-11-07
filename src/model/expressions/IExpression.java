package model.expressions;

import model.exceptions.ExpressionEvaluationException;
import model.utility.IDeepCopyable;
import model.utility.MyIDictionary;
import model.values.IValue;

public interface IExpression extends IDeepCopyable {
    String toString();
    IValue evaluate(MyIDictionary<String, IValue> symbolTable) throws ExpressionEvaluationException;
    IExpression deepCopy();
}

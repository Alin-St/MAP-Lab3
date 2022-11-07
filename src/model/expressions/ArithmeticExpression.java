package model.expressions;

import model.exceptions.ExpressionEvaluationException;
import model.types.IntType;
import model.utility.MyIDictionary;
import model.values.IValue;
import model.values.IntValue;

public class ArithmeticExpression implements IExpression {

    private final IExpression _leftOperand;
    private final IExpression _rightOperand;
    private final ArithmeticOperator _operator;

    public ArithmeticExpression(IExpression leftOperand, IExpression rightOperand, ArithmeticOperator operator) {
        _leftOperand = leftOperand;
        _rightOperand = rightOperand;
        _operator = operator;
    }

    @Override
    public String toString() {
        var lo_str = (_leftOperand instanceof ValueExpression || _leftOperand instanceof VariableExpression) ?
                _leftOperand.toString() :
                "(" + _leftOperand + ")";
        var op_str = switch (_operator) {
            case ADDITION -> "+";
            case SUBTRACTION -> "-";
            case MULTIPLICATION -> "*";
            case DIVISION -> "/";
        };
        var ro_str = (_rightOperand instanceof ValueExpression || _rightOperand instanceof VariableExpression) ?
                _rightOperand.toString() :
                "(" + _rightOperand + ")";
        return lo_str + " " + op_str + " " + ro_str;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symbolTable) throws ExpressionEvaluationException {
        var leftValue = _leftOperand.evaluate(symbolTable);
        if (!leftValue.getType().equals(IntType.get()))
            throw new ExpressionEvaluationException("First operand (of type '" + leftValue.getType().toString() + "') is not an integer.");

        var rightValue = _rightOperand.evaluate(symbolTable);
        if (!rightValue.getType().equals(IntType.get()))
            throw new ExpressionEvaluationException("Second operand (of type '" + rightValue.getType().toString() + "') is not an integer.");

        int v1 = ((IntValue)leftValue).getValue();
        int v2 = ((IntValue)rightValue).getValue();

        switch (_operator)
        {
            case ADDITION:
                return new IntValue(v1 + v2);

            case SUBTRACTION:
                return new IntValue(v1 - v2);

            case MULTIPLICATION:
                return new IntValue(v1 * v2);

            case DIVISION:
                if (v2 == 0)
                    throw new ExpressionEvaluationException("Division by zero ('" + this + "').");
                return new IntValue(v1 / v2);

            default:
                throw new ExpressionEvaluationException("Unknown arithmetic operation.");
        }
    }

    @Override
    public ArithmeticExpression deepCopy() {
        return new ArithmeticExpression(_leftOperand.deepCopy(), _rightOperand.deepCopy(), _operator);
    }
}

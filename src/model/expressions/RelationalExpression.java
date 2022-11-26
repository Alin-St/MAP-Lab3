package model.expressions;

import model.exceptions.ExpressionEvaluationException;
import model.programState.IHeapTable;
import model.programState.ISymbolTable;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

public class RelationalExpression implements IExpression {

    private final IExpression _leftOperand;
    private final IExpression _rightOperand;
    private final RelationalOperator _operator;

    public RelationalExpression(IExpression leftOperand, IExpression rightOperand, RelationalOperator operator) {
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
            case LESS_THAN -> "<";
            case LESS_OR_EQUAL -> "<=";
            case EQUALS -> "==";
            case NOT_EQUALS -> "!=";
            case GREATER_THAN -> ">";
            case GREATER_OR_EQUAL -> ">=";
        };
        var ro_str = (_rightOperand instanceof ValueExpression || _rightOperand instanceof VariableExpression) ?
                _rightOperand.toString() :
                "(" + _rightOperand + ")";
        return lo_str + " " + op_str + " " + ro_str;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) throws ExpressionEvaluationException {
        var leftValue = _leftOperand.evaluate(symbolTable, heapTable);
        if (!leftValue.getType().equals(IntType.get()))
            throw new ExpressionEvaluationException("First operand (of type '" + leftValue.getType().toString() + "') is not an integer.");

        var rightValue = _rightOperand.evaluate(symbolTable, heapTable);
        if (!rightValue.getType().equals(IntType.get()))
            throw new ExpressionEvaluationException("Second operand (of type '" + rightValue.getType().toString() + "') is not an integer.");

        int v1 = ((IntValue)leftValue).getValue();
        int v2 = ((IntValue)rightValue).getValue();

        return switch (_operator) {
            case LESS_THAN -> new BoolValue(v1 < v2);
            case LESS_OR_EQUAL -> new BoolValue(v1 <= v2);
            case EQUALS -> new BoolValue(v1 == v2);
            case NOT_EQUALS -> new BoolValue(v1 != v2);
            case GREATER_THAN -> new BoolValue(v1 > v2);
            case GREATER_OR_EQUAL -> new BoolValue(v1 >= v2);
        };
    }

    @Override
    public RelationalExpression deepCopy() {
        return new RelationalExpression(_leftOperand.deepCopy(), _rightOperand.deepCopy(), _operator);
    }
}

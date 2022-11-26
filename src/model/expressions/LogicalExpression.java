package model.expressions;

import model.exceptions.ExpressionEvaluationException;
import model.programState.IHeapTable;
import model.programState.ISymbolTable;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

public class LogicalExpression implements IExpression {

    private final IExpression _leftOperand;
    private final IExpression _rightOperand;
    private final LogicOperator _operator;

    public LogicalExpression(IExpression leftOperand, IExpression rightOperand, LogicOperator operator) {
        _leftOperand = leftOperand;
        _rightOperand = rightOperand;
        _operator = operator;
    }

    @Override
    public String toString() {
        var lo_str = (_leftOperand instanceof ValueExpression || _leftOperand instanceof VariableExpression) ?
                _leftOperand.toString() :
                "(" + _leftOperand + ")";
        var op_str = switch (_operator) { case AND -> "and"; case OR -> "or"; };
        var ro_str = (_rightOperand instanceof ValueExpression || _rightOperand instanceof VariableExpression) ?
                _rightOperand.toString() :
                "(" + _rightOperand + ")";
        return lo_str + " " + op_str + " " + ro_str;
    }

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) throws ExpressionEvaluationException {
        var leftValue = _leftOperand.evaluate(symbolTable, heapTable);
        if (!leftValue.getType().equals(BoolType.get()))
            throw new ExpressionEvaluationException("First operand (of type '" + leftValue.getType().toString() + "') is not a boolean.");

        var rightValue = _rightOperand.evaluate(symbolTable, heapTable);
        if (!rightValue.getType().equals(BoolType.get()))
            throw new ExpressionEvaluationException("Second operand (of type '" + rightValue.getType().toString() + "') is not a boolean.");

        boolean v1 = ((BoolValue)leftValue).getValue();
        boolean v2 = ((BoolValue)rightValue).getValue();

        return switch (_operator) {
            case AND -> new BoolValue(v1 && v2);
            case OR -> new BoolValue(v1 || v2);
        };
    }

    @Override
    public LogicalExpression deepCopy() {
        return new LogicalExpression(_leftOperand.deepCopy(), _rightOperand.deepCopy(), _operator);
    }
}

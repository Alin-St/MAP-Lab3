package model.statements;

import model.exceptions.InterpreterException;
import model.exceptions.StatementExecutionException;
import model.expressions.IExpression;
import model.programState.ProgramState;
import model.types.BoolType;
import model.values.BoolValue;

public class WhileStatement implements IStatement {

    private final IExpression _condition;
    private final IStatement _whileStatement;

    public WhileStatement(IExpression condition, IStatement whileStatement) {
        _condition = condition;
        _whileStatement = whileStatement;
    }

    @Override
    public String toString() {
        return "WHILE " + _condition + " EXECUTE\n" + _whileStatement.toString().indent(4) + "END_WHILE";
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        var symbolTable = state.getSymbolTable();
        var stack = state.getExecutionStack();
        var condVal = _condition.evaluate(symbolTable, state.getHeapTable());

        if (!condVal.getType().equals(BoolType.get()))
            throw new StatementExecutionException("Conditional expression is not a boolean.");

        boolean bCond = ((BoolValue)condVal).getValue();

        if (bCond) {
            stack.push(this);
            stack.push(_whileStatement);
        }

        return state;
    }

    @Override
    public WhileStatement deepCopy() {
        return new WhileStatement(_condition.deepCopy(), _whileStatement.deepCopy());
    }
}

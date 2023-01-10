package model.statements;

import model.programState.ProgramState;
import model.exceptions.InterpreterException;
import model.exceptions.StatementExecutionException;
import model.expressions.IExpression;
import model.types.BoolType;
import model.types.IType;
import model.utility.MyIDictionary;
import model.values.BoolValue;

public class ConditionalStatement implements IStatement {

    private final IExpression _condition;
    private final IStatement _thenStatement;
    private final IStatement _elseStatement;

    public ConditionalStatement(IExpression condition, IStatement thenStatement, IStatement elseStatement) {
        _condition = condition;
        _thenStatement = thenStatement;
        _elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        var ts = _thenStatement.toString().indent(4);
        return "IF " + _condition.toString() + " THEN\n" + _thenStatement.toString().indent(4) + "ELSE\n" +
                _elseStatement.toString().indent(4) + "END_IF";
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        var stack = state.getExecutionStack();
        var symbolTable = state.getSymbolTable();
        var condVal = _condition.evaluate(symbolTable, state.getHeapTable());

        if (!condVal.getType().equals(BoolType.get()))
            throw new StatementExecutionException("Conditional expression is not a boolean.");

        boolean bCond = ((BoolValue)condVal).getValue();

        if (bCond)
            stack.push(_thenStatement);
        else
            stack.push(_elseStatement);

        return null;
    }

    @Override
    public ConditionalStatement deepCopy() {
        return new ConditionalStatement(_condition.deepCopy(), _thenStatement.deepCopy(), _elseStatement.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws InterpreterException {
        if (!_condition.typeCheck(typeEnv).equals(BoolType.get()))
            throw new InterpreterException("Condition is not a boolean.");
        _thenStatement.typeCheck(typeEnv.deepCopy());
        _condition.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }
}
